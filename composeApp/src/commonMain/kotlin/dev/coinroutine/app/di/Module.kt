package dev.coinroutine.app.di

import androidx.room.RoomDatabase
import dev.coinroutine.app.coins.data.remote.impl.KtorCoinsRemoteDataSource
import dev.coinroutine.app.coins.domain.GetCoinDetailsUseCase
import dev.coinroutine.app.coins.domain.GetCoinPriceHistoryUseCase
import dev.coinroutine.app.coins.domain.GetCoinsListUseCase
import dev.coinroutine.app.coins.domain.api.CoinsRemoteDataSource
import dev.coinroutine.app.coins.presentation.CoinsListViewModel
import dev.coinroutine.app.core.database.portfolio.PortfolioDatabase
import dev.coinroutine.app.core.database.portfolio.getPortfolioDatabase
import dev.coinroutine.app.core.network.HttpClientFactory
import dev.coinroutine.app.portfolio.data.PortfolioRepositoryImpl
import dev.coinroutine.app.portfolio.domain.PortfolioRepository
import dev.coinroutine.app.portfolio.presentation.PortfolioViewModel
import dev.coinroutine.app.trade.domain.BuyCoinUseCase
import dev.coinroutine.app.trade.domain.SellCoinUseCase
import dev.coinroutine.app.trade.presentation.buy.BuyViewModel
import dev.coinroutine.app.trade.presentation.sell.SellViewModel
import io.ktor.client.HttpClient
import org.koin.core.context.startKoin
import org.koin.core.module.Module
import org.koin.core.module.dsl.singleOf
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.KoinAppDeclaration
import org.koin.dsl.bind
import org.koin.dsl.module

fun initKoin(config: KoinAppDeclaration? = null) =
    startKoin {
        config?.invoke(this)
        modules(
            sharedModule,
            platformModule,
        )
    }


expect val platformModule: Module

val sharedModule = module {

    // core
    single<HttpClient> { HttpClientFactory.create(get()) }

    // portfolio
    single {
        getPortfolioDatabase(get<RoomDatabase.Builder<PortfolioDatabase>>())
    }
    singleOf(::PortfolioRepositoryImpl).bind<PortfolioRepository>()
    single { get<PortfolioDatabase>().portfolioDao() }
    single { get<PortfolioDatabase>().userBalanceDao() }
    viewModel { PortfolioViewModel(get()) }

    // coins list
    viewModel { CoinsListViewModel(get(), get()) }
    singleOf(::GetCoinsListUseCase)
    singleOf(::KtorCoinsRemoteDataSource).bind<CoinsRemoteDataSource>()
    singleOf(::GetCoinDetailsUseCase)
    singleOf(::GetCoinPriceHistoryUseCase)

    // trade
    singleOf(::BuyCoinUseCase)
    singleOf(::SellCoinUseCase)
    viewModel { BuyViewModel(get(), get(), get()) }
    viewModel { SellViewModel(get(), get(), get()) }
}