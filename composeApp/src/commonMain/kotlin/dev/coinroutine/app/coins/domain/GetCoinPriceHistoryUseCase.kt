package dev.coinroutine.app.coins.domain

import dev.coinroutine.app.coins.data.mapper.toPriceModel
import dev.coinroutine.app.coins.domain.api.CoinsRemoteDataSource
import dev.coinroutine.app.coins.domain.model.PriceModel
import dev.coinroutine.app.core.domain.DataError
import dev.coinroutine.app.core.domain.Result
import dev.coinroutine.app.core.domain.map

class GetCoinPriceHistoryUseCase(
    private val client: CoinsRemoteDataSource,
) {

    suspend fun execute(coinId: String): Result<List<PriceModel>, DataError.Remote> {
        return client.getPriceHistory(coinId).map { dto ->
            dto.data.history.map { it.toPriceModel() }
        }
    }
}