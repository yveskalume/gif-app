package com.yvkalume.gifapp.data.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import io.ktor.client.HttpClient
import io.ktor.client.engine.android.Android
import javax.inject.Singleton
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json


@Module
@InstallIn(SingletonComponent::class)
object DataSourceModule {

		@Provides
		@Singleton
		fun provideKtor(): HttpClient {
				return HttpClient(Android) {
						expectSuccess = true
						install(ContentNegotiation) {
								json(Json {
										isLenient = true
										ignoreUnknownKeys = true
										prettyPrint = true
								})
						}
				}
		}
}