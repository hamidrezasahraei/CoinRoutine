package dev.coinroutine.app.coins.domain.model

import dev.coinroutine.app.core.domain.coin.Coin

data class CoinModel(
    val coin: Coin,
    val price: Double,
    val change: Double,
)