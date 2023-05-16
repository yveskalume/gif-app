package com.yvkalume.gifapp.app

import androidx.compose.runtime.Composable
import androidx.compose.runtime.Stable
import androidx.compose.runtime.remember
import androidx.navigation.NavDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.yvkalume.gifapp.ui.util.Destination
import com.yvkalume.gifapp.ui.util.Destination.Favorites
import com.yvkalume.gifapp.ui.util.Destination.Home

@Composable
fun rememberAppState(navController: NavHostController) = remember {
    GifAppState(navController)
}

@Stable
class GifAppState(private val navController: NavHostController) {

    val currentDestination: NavDestination?
        @Composable get() = navController
            .currentBackStackEntryAsState().value?.destination

    val currentTopAppBarTitle: String?
        @Composable get() = when (currentDestination?.route) {
            Home.route -> Home.topBarText
            Favorites.route -> Favorites.topBarText
            else -> null
        }

    val topLevelDestination = Destination.values().filter { it.isTopLeveDestination }

}