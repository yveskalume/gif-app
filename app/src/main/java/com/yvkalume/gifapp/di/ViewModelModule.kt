package com.yvkalume.gifapp.di

import com.yvkalume.gifapp.di.mavericks.AssistedViewModelFactory
import com.yvkalume.gifapp.di.mavericks.MavericksViewModelComponent
import com.yvkalume.gifapp.di.mavericks.ViewModelKey
import com.yvkalume.gifapp.ui.screen.favorite.logic.FavoriteViewModel
import com.yvkalume.gifapp.ui.screen.home.logic.HomeViewModel
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.multibindings.IntoMap

@Module
@InstallIn(MavericksViewModelComponent::class)
interface ViewModelModule {

    @Binds
    @IntoMap
    @ViewModelKey(HomeViewModel::class)
    fun homeViewModel(factory: HomeViewModel.Factory): AssistedViewModelFactory<*, *>

    @Binds
    @IntoMap
    @ViewModelKey(FavoriteViewModel::class)
    fun favoriteViewModel(factory: FavoriteViewModel.Factory): AssistedViewModelFactory<*, *>
}