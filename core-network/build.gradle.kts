plugins {
    alias(libs.plugins.androidLibrary)
    alias(libs.plugins.jetbrains.kotlin.android)
    alias(libs.plugins.jetbrainsKotlinKapt)
    alias(libs.plugins.hiltAndroid)
}

android {
    namespace = "com.alfayedOficial.coreNetwork"
    compileSdk = 34

    defaultConfig {
        minSdk = 24

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        consumerProguardFiles("consumer-rules.pro")
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
}

dependencies {

    /*-----------Thread Dependencies---------*/
    api(libs.kotlinx.coroutines.android)
    api(libs.kotlinx.coroutines.core)
    kapt(libs.hilt.android.compiler)
    implementation(libs.hilt.android)
    api(libs.androidx.hilt.navigation.compose)

    /*-----------Network Dependencies--------*/
    api(libs.retrofit)
    api(libs.converter.moshi)
    api(libs.okhttp)
    api(libs.logging.interceptor)
}