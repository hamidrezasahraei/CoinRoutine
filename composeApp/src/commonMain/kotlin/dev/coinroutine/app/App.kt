package dev.coinroutine.app

import androidx.compose.runtime.Composable
import dev.coinroutine.app.coins.presentation.CoinsListScreen
import dev.coinroutine.app.portfolio.presentation.PortfolioScreen
import dev.coinroutine.app.theme.CoinRoutineTheme
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
@Preview
fun App() {
    CoinRoutineTheme {
//        CoinsListScreen {  }
        PortfolioScreen(
            onCoinItemClicked = {},
            onDiscoverCoinsClicked = {}
        )
    }
}