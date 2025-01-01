package dev.coinroutine.app.coins.data.mapper

import dev.coinroutine.app.coins.data.remote.dto.CoinItemDto
import dev.coinroutine.app.coins.data.remote.dto.CoinPriceDto
import dev.coinroutine.app.coins.domain.model.CoinModel
import dev.coinroutine.app.coins.domain.model.PriceModel
import dev.coinroutine.app.core.domain.coin.Coin

fun CoinItemDto.toCoinModel() = CoinModel(
    coin = Coin(
        id = uuid,
        name = name,
        symbol = symbol,
        iconUrl = iconUrl,
    ),
    price = price,
    change = change,
)

fun CoinPriceDto.toPriceModel() = PriceModel(
    price = price ?: 0.0,
    timestamp = timestamp,
)