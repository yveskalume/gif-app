package com.yvkalume.gifapp.data.di

import android.content.Context
import androidx.room.Room
import com.yvkalume.gifapp.data.room.AppDatabase
import com.yvkalume.gifapp.data.room.dao.GifDao
import com.yvkalume.gifapp.data.room.dao.StickerDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RoomModule {

		@Singleton
		@Provides
		fun provideDatabase(@ApplicationContext context: Context): AppDatabase {
				return Room.databaseBuilder(
						context,
						AppDatabase::class.java, "database-name"
				).build()
		}

		@Provides
		fun provideGifDao(db: AppDatabase): GifDao {
				return db.gifDao()
		}

		@Provides
		fun provideStickerDao(db: AppDatabase) : StickerDao {
				return db.stickerDao()
		}
}