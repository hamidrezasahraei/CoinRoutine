package dev.coinroutine.app

import androidx.compose.ui.window.ComposeUIViewController
import dev.coinroutine.app.di.initKoin

fun MainViewController() = ComposeUIViewController(
    configure = {
        initKoin()
    }
) { App() }