package dev.coinroutine.app

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform