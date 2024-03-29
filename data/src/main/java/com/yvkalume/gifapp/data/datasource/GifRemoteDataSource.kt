package com.yvkalume.gifapp.data.datasource

import com.yvkalume.gifapp.data.model.server.GiphyHttpResponse
import com.yvkalume.gifapp.data.util.ServerEndpoints
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import javax.inject.Inject

class GifRemoteDataSource @Inject constructor(private val httpClient: HttpClient) {

		suspend fun getAllTrending(): GiphyHttpResponse {
				return httpClient.get(ServerEndpoints.trendingGifs).body()
		}
}