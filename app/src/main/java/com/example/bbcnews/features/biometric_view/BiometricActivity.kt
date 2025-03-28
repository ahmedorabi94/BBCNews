package com.example.bbcnews.features.biometric_view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.biometric.BiometricPrompt
import com.example.bbcnews.MainActivity
import com.example.bbcnews.R
import timber.log.Timber

class BiometricActivity : AppCompatActivity() , BiometricAuthListener {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_biometric)
        if (BiometricUtil.isBiometricReady(this)){
            BiometricUtil.showBiometricPrompt(
                activity = this,
                listener = this,
                cryptoObject = null,
                allowDeviceCredential = true
            )
        }else{
            startActivity(Intent(this,MainActivity::class.java))
            finish()
        }

    }

    override fun onBiometricAuthenticationSuccess(result: BiometricPrompt.AuthenticationResult) {
        Timber.e("onBiometricAuthenticationSuccess")
        startActivity(Intent(this,MainActivity::class.java))
        finish()
    }


    override fun onBiometricAuthenticationError(errorCode: Int, errorMessage: String) {
        Timber.e("onBiometricAuthenticationError")
    }
}