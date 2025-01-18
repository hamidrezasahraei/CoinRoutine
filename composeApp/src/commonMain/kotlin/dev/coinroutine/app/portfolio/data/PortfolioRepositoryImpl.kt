package dev.coinroutine.app.portfolio.data

import dev.coinroutine.app.coins.domain.api.CoinsRemoteDataSource
import dev.coinroutine.app.core.domain.DataError
import dev.coinroutine.app.core.domain.Result
import dev.coinroutine.app.core.domain.onError
import dev.coinroutine.app.core.domain.onSuccess
import dev.coinroutine.app.portfolio.data.local.PortfolioDao
import dev.coinroutine.app.portfolio.data.local.UserBalanceDao
import dev.coinroutine.app.portfolio.data.local.UserBalanceEntity
import dev.coinroutine.app.portfolio.data.mapper.toPortfolioCoinModel
import dev.coinroutine.app.portfolio.domain.PortfolioCoinModel
import dev.coinroutine.app.portfolio.domain.PortfolioRepository
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.flow

class PortfolioRepositoryImpl(
    private val portfolioDao: PortfolioDao,
    private val userBalanceDao: UserBalanceDao,
    private val coinsRemoteDataSource: CoinsRemoteDataSource,
) : PortfolioRepository{

    override suspend fun initializeBalance() {
        val cashBalance = userBalanceDao.getCashBalance()
        if (cashBalance == null) {
            userBalanceDao.insertBalance(
                UserBalanceEntity(cashBalance = 10000.0)
            )
        }
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    override fun allPortfolioCoinsFlow(): Flow<Result<List<PortfolioCoinModel>, DataError.Remote>> {
        return portfolioDao.getAllOwnedCoins().flatMapLatest { portfolioCoinsEntities ->
            if (portfolioCoinsEntities.isEmpty()) {
                flow {
                    emit(Result.Success(emptyList<PortfolioCoinModel>()))
                }
            } else {
                flow {
                    coinsRemoteDataSource.getListOfCoins()
                        .onError { error ->
                            emit(Result.Error(error))
                        }
                        .onSuccess { coinsDto ->
                            val portfolioCoins = portfolioCoinsEntities.mapNotNull { portfolioCoinsEntity ->
                                val coin = coinsDto.data.coins.find { it.uuid == portfolioCoinsEntity.coinId }
                                coin?.let {
                                    portfolioCoinsEntity.toPortfolioCoinModel(it.price)
                                }
                            }
                            emit(Result.Success(portfolioCoins))
                        }
                }
            }
        }.catch {
            emit(Result.Error(DataError.Remote.UNKNOWN))
        }
    }
}