package com.yvkalume.gifapp.data.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import io.ktor.client.HttpClient
import io.ktor.client.engine.HttpClientEngine
import io.ktor.client.engine.android.Android
import io.ktor.client.engine.okhttp.OkHttp
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.serialization.kotlinx.json.json
import javax.inject.Singleton
import kotlinx.serialization.json.Json

@Module
@InstallIn(SingletonComponent::class)
object DataSourceModule {

		@Provides
		@Singleton
		fun provideHttpClientEngine() : HttpClientEngine {
				return OkHttp.create()
		}
		@Provides
		@Singleton
		fun provideKtor(httpClientEngine: HttpClientEngine): HttpClient {
				return HttpClient(httpClientEngine) {
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