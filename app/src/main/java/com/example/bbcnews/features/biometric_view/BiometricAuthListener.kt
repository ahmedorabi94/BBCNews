package com.example.bbcnews.features.biometric_view

import androidx.biometric.BiometricPrompt


interface BiometricAuthListener {
    fun onBiometricAuthenticationSuccess(result: BiometricPrompt.AuthenticationResult)
    fun onBiometricAuthenticationError(errorCode: Int, errorMessage: String)
}