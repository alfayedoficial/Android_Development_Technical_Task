plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.jetbrains.kotlin.android)
    alias(libs.plugins.jetbrainsKotlinKapt)
    alias(libs.plugins.hiltAndroid)

}

android {
    namespace = "com.alfayedoficial.androiddevelopmenttechnicaltask"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.aligntech.myapplicationandroiddevelopmenttechnicaltask"
        minSdk = 24
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables {
            useSupportLibrary = true
        }
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
    buildFeatures {
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = "1.5.1"
    }
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
}

dependencies {

    implementation(project(":core-network"))
    implementation(project(":core-local"))

    /*-----------Main Dependencies-----------*/
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.androidx.activity.compose)
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.ui)
    implementation(libs.androidx.ui.graphics)
    implementation(libs.androidx.ui.tooling.preview)
    implementation(libs.androidx.material3)
    implementation(libs.androidx.navigation.compose)
    implementation(libs.androidx.navigation.ui.ktx)

    implementation(libs.coil.compose)
    implementation( libs.androidx.paging.runtime.ktx)
    implementation( libs.androidx.paging.compose)
    implementation(libs.androidx.lifecycle.runtime.ktx.v262)
    implementation( libs.androidx.lifecycle.runtime.compose)
    implementation( libs.androidx.lifecycle.viewmodel.compose)
    implementation(libs.androidx.material.icons.extended)
    implementation(libs.androidx.constraintlayout.compose)

    /*-----------Thread Dependencies---------*/
    kapt(libs.hilt.android.compiler)
    implementation(libs.hilt.android)


    /*-----------Test Dependencies-----------*/
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(platform(libs.androidx.compose.bom))
    androidTestImplementation(libs.androidx.ui.test.junit4)
    debugImplementation(libs.androidx.ui.tooling)
    debugImplementation(libs.androidx.ui.test.manifest)

    implementation (libs.androidx.core)
    testImplementation(libs.androidx.core)
    testImplementation(libs.androidx.runner)
    testImplementation(libs.androidx.rules)
    testImplementation(libs.hamcrest.all)
    testImplementation(libs.robolectric)
    testImplementation(libs.mockito.core)
    testImplementation(libs.truth)
    testImplementation(libs.androidx.core.testing)
    testImplementation(libs.kotlinx.coroutines.test.v171)
    testImplementation(libs.mockk)
    testImplementation(libs.turbine)
}
