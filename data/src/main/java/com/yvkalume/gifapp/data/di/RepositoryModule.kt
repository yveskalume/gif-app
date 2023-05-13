package com.yvkalume.gifapp.data.di

import com.yvkalume.gifapp.data.datasource.GifLocalDataSource
import com.yvkalume.gifapp.data.datasource.GifRemoteDataSource
import com.yvkalume.gifapp.data.repository.GifRepositoryImpl
import com.yvkalume.gifapp.data.util.IoDispatcher
import com.yvkalume.gifapp.domain.repository.GifRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineDispatcher
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Provides
    @Singleton
    fun provideGifRepository(
        remoteDataSource: GifRemoteDataSource,
        localDataSource: GifLocalDataSource,
        @IoDispatcher coroutineDispatcher: CoroutineDispatcher
    ): GifRepository {
        return GifRepositoryImpl(
            remoteDataSource, localDataSource, coroutineDispatcher
        )
    }
}