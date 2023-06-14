plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    id("dagger.hilt.android.plugin")
    kotlin("kapt")
}

android {
    namespace = "com.yvkalume.gifapp"
    compileSdk = 33

    signingConfigs {
        create("release") {
            storeFile = file("/Users/yves-kalume/Documents/developerkey.jks")
            storePassword = " "
            keyAlias = " "
            keyPassword = " "
        }
    }

    defaultConfig {
        applicationId = "com.yvkalume.gifapp"
        minSdk = 24
        targetSdk = 33
        versionCode = 3
        versionName = "1.2"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables {
            useSupportLibrary = true
        }
    }

    buildTypes {
        release {
            isMinifyEnabled = true
            isShrinkResources = true
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro"
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
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = "1.4.2"
    }
    packagingOptions {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
}

dependencies {

    implementation(project(":data"))

    implementation(libs.core.ktx)
    implementation(libs.lifecycle.ktx)
    implementation(libs.bundles.compose)
    implementation(libs.compose.material)
    implementation(libs.compose.material3)

    implementation(libs.coil.compose)
    implementation(libs.coil.gif)

    implementation(libs.hilt.android)
    implementation(libs.hilt.navigation.compose)
    kapt(libs.hilt.compiler)
    testImplementation(libs.hilt.test)

    implementation(libs.pager)
    implementation(libs.pager.indicators)

    implementation(libs.lottie.compose)

    debugImplementation(libs.bundles.compose.debug)

    testImplementation(libs.junit.test)
    androidTestImplementation(libs.junit.androidTest)
    androidTestImplementation(libs.espresso.androidTest)
    testImplementation(libs.mockk)

    implementation(libs.coroutine)
    testImplementation(libs.coroutine.test)

    implementation(libs.mavericks)
    implementation(libs.mavericks.compose)
    testImplementation(libs.mavericks.testing)


    implementation(libs.paging.compose)

    implementation(libs.accompanist.permision)

    implementation(libs.modernStorage.permission)

    testImplementation(libs.turbine)
}

kapt {
    correctErrorTypes = true
}