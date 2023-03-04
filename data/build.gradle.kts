plugins {
		id("com.android.library")
		id("org.jetbrains.kotlin.android")
		id("dagger.hilt.android.plugin")
		id("com.google.devtools.ksp")
		kotlin("kapt")
		kotlin("plugin.serialization") version "1.8.10"
		id("com.google.android.libraries.mapsplatform.secrets-gradle-plugin")

}

android {
		namespace = "com.yvkalume.gifapp.data"
		compileSdk = 33

		defaultConfig {
				minSdk = 24
				targetSdk = 33

				testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
				consumerProguardFiles("consumer-rules.pro")
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
}

dependencies {
		implementation(libs.core.ktx)
		implementation(libs.lifecycle.ktx)

		testImplementation(libs.junit.test)
		androidTestImplementation(libs.junit.androidTest)
		androidTestImplementation(libs.espresso.androidTest)
		testImplementation(libs.mockk)

		implementation(libs.ktor.core)
		implementation(libs.ktor.android)
		implementation(libs.ktor.serialization)
		implementation(libs.ktor.contentnegotiation)

		implementation(libs.kotlin.serialization)

		implementation(libs.hilt.android)
		kapt(libs.hilt.compiler)
		testImplementation(libs.hilt.test)

		implementation(libs.coroutine)
		testImplementation(libs.coroutine.test)

		implementation(libs.room.runtime)
		implementation(libs.room.ktx)
		ksp(libs.room.ksp)
		testImplementation(libs.room.test)
}

secrets {
		// Change the properties file from the default "local.properties" in your root project
		// to another properties file in your root project.
		propertiesFileName = "secrets.properties"

		// A properties file containing default secret values. This file can be checked in version
		// control.
		defaultPropertiesFileName = "secrets.sample.properties"

}