package dev.coinroutine.app

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import dev.coinroutine.app.coins.presentation.CoinsListScreen
import dev.coinroutine.app.core.navigation.Buy
import dev.coinroutine.app.core.navigation.Coins
import dev.coinroutine.app.core.navigation.Portfolio
import dev.coinroutine.app.core.navigation.Sell
import dev.coinroutine.app.portfolio.presentation.PortfolioScreen
import dev.coinroutine.app.theme.CoinRoutineTheme
import dev.coinroutine.app.trade.presentation.buy.BuyScreen
import dev.coinroutine.app.trade.presentation.sell.SellScreen
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
@Preview
fun App() {
    val navController: NavHostController = rememberNavController()
    CoinRoutineTheme {
        NavHost(
            navController = navController,
            startDestination = Portfolio,
            modifier = Modifier.fillMaxSize()
        ) {
            composable<Portfolio> {
                PortfolioScreen(
                    onCoinItemClicked = { coinId -> // TODO: will be used later
                        navController.navigate(Sell)
                    },
                    onDiscoverCoinsClicked = {
                        navController.navigate(Coins)
                    }
                )
            }

            composable<Coins> {
                CoinsListScreen { coinId -> // TODO: will be used later
                    navController.navigate(Buy)
                }
            }

            composable<Buy> { navBackStackEntry ->
                BuyScreen(
                    coinId = "todo",
                    navigateToPortfolio = {
                        navController.navigate(Portfolio) {
                            popUpTo(Portfolio) { inclusive = true }
                        }
                    }
                )
            }
            composable<Sell> { navBackStackEntry ->
                SellScreen(
                    coinId = "todo",
                    navigateToPortfolio = {
                        navController.navigate(Portfolio) {
                            popUpTo(Portfolio) { inclusive = true }
                        }
                    }
                )
            }

        }
    }
}