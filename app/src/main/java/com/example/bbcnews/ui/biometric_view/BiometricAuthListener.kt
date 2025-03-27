package com.example.bbcnews.ui.biometric_view

import androidx.biometric.BiometricPrompt


interface BiometricAuthListener {
    fun onBiometricAuthenticationSuccess(result: BiometricPrompt.AuthenticationResult)
    fun onBiometricAuthenticationError(errorCode: Int, errorMessage: String)
}