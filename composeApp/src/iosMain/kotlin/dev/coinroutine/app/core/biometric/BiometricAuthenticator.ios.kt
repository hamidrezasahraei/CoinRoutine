package dev.coinroutine.app.core.biometric

import androidx.compose.runtime.Composable
import dev.coinroutine.app.biometric.IosBiometricAuthenticator

object IosPlatformContext : PlatformContext

@Composable
actual fun getPlatformContext(): PlatformContext = IosPlatformContext

actual fun getBiometricAuthenticator(context: PlatformContext): BiometricAuthenticator =
    IosBiometricAuthenticator()