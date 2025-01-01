package dev.coinroutine.app.core.domain.coin

data class Coin(
    val id: String,
    val name: String,
    val symbol: String,
    val iconUrl: String,
)