package com.yvkalume.gifapp.data.di

import com.yvkalume.gifapp.data.datasource.gif.GifLocalDataSource
import com.yvkalume.gifapp.data.datasource.gif.GifRemoteDataSource
import com.yvkalume.gifapp.data.datasource.sticker.StickerLocalDataSource
import com.yvkalume.gifapp.data.datasource.sticker.StickerRemoteDataSource
import com.yvkalume.gifapp.data.repository.GifRepositoryImpl
import com.yvkalume.gifapp.data.repository.StickerRepositoryImpl
import com.yvkalume.gifapp.data.util.IoDispatcher
import com.yvkalume.gifapp.domain.repository.GifRepository
import com.yvkalume.gifapp.domain.repository.StickerRepository
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
    fun provideStickerRepository(
        remoteDataSource: StickerRemoteDataSource,
        localDataSource: StickerLocalDataSource,
        @IoDispatcher coroutineDispatcher: CoroutineDispatcher
    ): StickerRepository {
        return StickerRepositoryImpl(
            remoteDataSource,
            localDataSource,
            coroutineDispatcher
        )
    }

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