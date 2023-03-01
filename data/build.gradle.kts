plugins {
		id("com.android.library")
		id("org.jetbrains.kotlin.android")
		id("dagger.hilt.android.plugin")
		kotlin("kapt")
		kotlin("plugin.serialization") version "1.8.10"

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

		implementation(libs.ktor.core)
		implementation(libs.ktor.android)
		implementation(libs.ktor.serialization)
		implementation(libs.ktor.contentnegotiation)



		implementation(libs.kotlin.serialization)

		implementation(libs.hilt.android)
		kapt(libs.hilt.compiler)
}