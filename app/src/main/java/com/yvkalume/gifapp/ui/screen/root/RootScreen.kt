package com.yvkalume.gifapp.ui.screen.root

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.yvkalume.gifapp.ui.screen.favorite.FavoriteRoute
import com.yvkalume.gifapp.ui.screen.home.HomeRoute
import com.yvkalume.gifapp.ui.util.Destination
import com.yvkalume.gifapp.ui.util.isCurrent

@Composable
fun RootScreen(modifier: Modifier = Modifier) {
    val navController = rememberNavController()
    Scaffold(
        modifier = modifier,
        bottomBar = {
            BottomNavigation(
                backgroundColor = Color.White
            ) {
                val navBackStackEntry by navController.currentBackStackEntryAsState()
                val currentDestination = navBackStackEntry?.destination

                Destination.values().forEach { destination ->
                    BottomNavigationItem(
                        selected = currentDestination.isCurrent(destination.route),
                        onClick = {
                            navController.navigate(destination.route) {
                                popUpTo(navController.graph.findStartDestination().id) {
                                    saveState = true
                                }
                                launchSingleTop = true
                                restoreState = true
                            }
                        },
                        icon = {
                            Icon(
                                imageVector = destination.icon,
                                contentDescription = stringResource(id = destination.label),
                                tint = if (currentDestination.isCurrent(destination.route)) {
                                    MaterialTheme.colors.primary
                                } else {
                                    Color.Black
                                }
                            )
                        },
                        label = {
                            Text(
                                text = stringResource(id = destination.label),
                                color = if (currentDestination.isCurrent(destination.route)) {
                                    MaterialTheme.colors.primary
                                } else {
                                    Color.Black
                                }
                            )
                        }
                    )
                }
            }
        }
    ) { contentPadding ->
        NavHost(
            navController = navController,
            startDestination = Destination.Home.route,
            modifier = Modifier
                .fillMaxSize()
                .padding(contentPadding)
        ) {
            composable(Destination.Home.route) {
                HomeRoute()
            }

            composable(Destination.Favorites.route) {
                FavoriteRoute()
            }
        }
    }
}