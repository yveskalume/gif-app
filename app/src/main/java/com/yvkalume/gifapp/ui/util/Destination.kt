package com.yvkalume.gifapp.ui.util

import androidx.annotation.StringRes
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Favorite
import androidx.compose.material.icons.rounded.Home
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.navigation.NavDestination
import androidx.navigation.NavDestination.Companion.hierarchy
import com.yvkalume.gifapp.R

enum class Destination(
    val route: String,
    @StringRes val label: Int,
    val icon: ImageVector,
    val isTopLeveDestination: Boolean = false,
    val topBarText: String? = null
) {
    Home("home", R.string.home, Icons.Rounded.Home, true, "GifApp"),
    Favorites(
        "favorites",
        R.string.favorites,
        Icons.Rounded.Favorite,
        true,
        topBarText = "Favorites"
    )
}

fun NavDestination?.isCurrent(route: String): Boolean {
    return this?.hierarchy?.any { it.route == route } == true
}