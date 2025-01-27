package dev.coinroutine.app

sealed class Platform {
    data object Android: Platform()
    data object Ios: Platform()
}

expect val platform: Platform