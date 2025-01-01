package dev.coinroutine.app.coins.domain

import dev.coinroutine.app.coins.data.mapper.toCoinModel
import dev.coinroutine.app.coins.domain.api.CoinsRemoteDataSource
import dev.coinroutine.app.coins.domain.model.CoinModel
import dev.coinroutine.app.core.domain.DataError
import dev.coinroutine.app.core.domain.Result
import dev.coinroutine.app.core.domain.map

class GetCoinDetailsUseCase(
    private val client: CoinsRemoteDataSource,
) {

    suspend fun execute(coinId: String): Result<CoinModel, DataError.Remote> {
        return client.getCoinById(coinId).map { dto ->
            dto.data.coin.toCoinModel()
        }
    }
}