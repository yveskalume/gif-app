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
import javax.inject.Singleton
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineScope

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

		@Provides
		@Singleton
		fun provideStickerRepository(
				remoteDataSource: StickerRemoteDataSource,
				localDataSource: StickerLocalDataSource,
				coroutineScope: CoroutineScope,
				@IoDispatcher coroutineDispatcher: CoroutineDispatcher
		): StickerRepository {
				return StickerRepositoryImpl(
						remoteDataSource,
						localDataSource,
						coroutineScope,
						coroutineDispatcher
				)
		}

		@Provides
		@Singleton
		fun provideGifRepository(
				remoteDataSource: GifRemoteDataSource,
				localDataSource: GifLocalDataSource,
				coroutineScope: CoroutineScope,
				@IoDispatcher coroutineDispatcher: CoroutineDispatcher
		): GifRepository {
				return GifRepositoryImpl(
						remoteDataSource, localDataSource, coroutineScope, coroutineDispatcher
				)
		}
}