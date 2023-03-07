package com.yvkalume.gifapp.data.di

import com.yvkalume.gifapp.data.util.IoDispatcher
import com.yvkalume.gifapp.data.util.MainDispatcher
import com.yvkalume.gifapp.data.util.MainImmediateDispatcher
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob

@Module
@InstallIn(SingletonComponent::class)
object CoroutineModule {

		@Provides
		fun provideCoroutineScope(): CoroutineScope = CoroutineScope(SupervisorJob())

		@Provides
		@IoDispatcher
		fun providesIoDispatcher(): CoroutineDispatcher = Dispatchers.IO

		@Provides
		@MainDispatcher
		fun providesMainDispatcher(): CoroutineDispatcher = Dispatchers.Main

		@Provides
		@MainImmediateDispatcher
		fun providesMainImmediateDispatcher(): CoroutineDispatcher = Dispatchers.Main.immediate
}