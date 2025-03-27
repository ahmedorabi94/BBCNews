plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.kotlin.compose)
    id("kotlin-kapt")
    id("com.google.dagger.hilt.android")
    id("kotlin-parcelize")


}

android {
    namespace = "com.example.bbcnews"
    compileSdk = 35

    defaultConfig {
        applicationId = "com.example.bbcnews"
        minSdk = 24
        targetSdk = 35
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
    kotlinOptions {
        jvmTarget = "11"
    }
    buildFeatures {
        buildConfig = true
        compose = true
    }

    flavorDimensions("news")
    productFlavors {
        create("bbc") {
            dimension = "news"
            buildConfigField("String", "sources", "\"bbc-news\"")
            manifestPlaceholders["variant"] = "BBC']"
            applicationIdSuffix = ".bbc"
            versionNameSuffix = ".bbc"

        }
        create("espn") {
            dimension = "news"
            buildConfigField("String", "sources", "\"espn\"")
            manifestPlaceholders["variant"] = "ESPN"
            applicationIdSuffix = ".espn"
            versionNameSuffix = ".espn"
        }
        create("bbcSport") {
            dimension = "news"
            buildConfigField("String", "sources", "\"bbc-sport\"")
            manifestPlaceholders["variant"] = "BBC Sport"
            applicationIdSuffix = ".bbcsport"
            versionNameSuffix = ".bbcsport"
        }
    }

    packaging {
        resources {
            excludes += setOf(
                "META-INF/LICENSE.md",
                "META-INF/LICENSE-notice.md",
                "META-INF/AL2.0",
                "META-INF/LGPL2.1"
            )
        }
    }
}

dependencies {

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.androidx.activity.compose)
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.ui)
    implementation(libs.androidx.ui.graphics)
    implementation(libs.androidx.ui.tooling.preview)
    implementation(libs.androidx.material3)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(platform(libs.androidx.compose.bom))
    androidTestImplementation(libs.androidx.ui.test.junit4)
    debugImplementation(libs.androidx.ui.tooling)
    debugImplementation(libs.androidx.ui.test.manifest)


    implementation(libs.coil.compose)

    implementation(libs.hilt.android)
    kapt(libs.hilt.compiler)

    implementation(libs.androidx.hilt.navigation.compose)


    // Retrofit & GSON
    implementation(libs.retrofit)
    implementation(libs.converter.gson)
    implementation(libs.logging.interceptor)
    implementation(libs.converter.moshi)
    implementation(libs.retrofit2.kotlin.coroutines.adapter)


    // Navigation
    implementation(libs.androidx.navigation.fragment.ktx)
    implementation(libs.androidx.navigation.ui.ktx)
    androidTestImplementation(libs.androidx.navigation.testing)

    // ViewModel and LiveData
    implementation(libs.androidx.lifecycle.viewmodel.ktx)
    implementation(libs.androidx.lifecycle.livedata.ktx)
    implementation(libs.androidx.lifecycle.common.java8)
    implementation(libs.androidx.lifecycle.extensions)

    // Coroutines
    implementation(libs.kotlinx.coroutines.core)
    implementation(libs.kotlinx.coroutines.android)


    // Timber
    implementation(libs.timber)

    // Glide
    implementation(libs.glide)
    kapt(libs.compiler)

    // Biometric
    implementation(libs.androidx.biometric)

    // Testing
   // testImplementation(libs.junit.jupiter.api)
    testImplementation(libs.androidx.core)
    testImplementation(libs.mockito.core)
    androidTestImplementation(libs.mockito.android)
    testImplementation(libs.mockito.inline)
    testImplementation(libs.androidx.core.testing)
    testImplementation(libs.kotlinx.coroutines.test)
    testImplementation(libs.mockwebserver)
    androidTestImplementation(libs.androidx.junit.v115)
    implementation(libs.multidex)

    androidTestImplementation(libs.androidx.core.testing)

    implementation(libs.androidx.espresso.idling.resource)

    // For instrumented tests.
    androidTestImplementation(libs.hilt.android.testing)
    // ...with Kotlin.
    kaptAndroidTest(libs.hilt.android.compiler)
    androidTestImplementation(libs.androidx.rules)
    androidTestImplementation(libs.androidx.runner)
    androidTestImplementation(libs.androidx.core)
    androidTestImplementation(libs.androidx.junit.ktx)

    androidTestImplementation(libs.androidx.espresso.core)

    androidTestImplementation("com.android.support.test.espresso:espresso-core:3.0.2") {
        exclude(group = "com.android.support", module = "support-annotations")
    }

    androidTestImplementation("com.android.support.test:runner:1.0.2") {
        exclude(group = "com.android.support", module = "support-annotations")
    }
    androidTestImplementation("com.android.support.test:rules:1.0.2") {
        exclude(group = "com.android.support", module = "support-annotations")
    }

    androidTestImplementation("com.android.support.test.espresso:espresso-intents:3.0.2") {
        exclude(group = "com.android.support", module = "support-annotations")
    }


    androidTestImplementation(libs.ui.test.junit4)
    debugImplementation(libs.ui.test.manifest)

    // Espresso dependencies (if you need to mix with View-based tests)
  //  androidTestImplementation (libs.androidx.espresso.core)

    testImplementation(libs.mockk)
    androidTestImplementation(libs.mockk.android)

}