package dev.coinroutine.app.trade.presentation.mapper

import dev.coinroutine.app.core.domain.coin.Coin
import dev.coinroutine.app.trade.presentation.common.UiTradeCoinItem

fun UiTradeCoinItem.toCoin() = Coin(
    id = id,
    name = name,
    symbol = symbol,
    iconUrl = iconUrl,
)