package com.yvkalume.gifapp.data.di

import android.content.Context
import androidx.room.Room
import com.yvkalume.gifapp.data.room.AppDatabase
import com.yvkalume.gifapp.data.room.dao.GifDao
import com.yvkalume.gifapp.data.room.migrations.MIGRATION_1_2
import com.yvkalume.gifapp.data.room.migrations.MIGRATION_2_3
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
        ).addMigrations(MIGRATION_1_2, MIGRATION_2_3).build()
    }

    @Provides
    fun provideGifDao(db: AppDatabase): GifDao {
        return db.gifDao()
    }
}