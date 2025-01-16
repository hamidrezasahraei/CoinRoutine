package dev.coinroutine.app.di

import io.ktor.client.engine.HttpClientEngine
import io.ktor.client.engine.android.Android
import org.koin.dsl.module

actual val platformModule = module {

    // core
    single<HttpClientEngine> { Android.create() }
}