package com.yvkalume.gifapp.ui.screen.root

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.yvkalume.gifapp.app.rememberAppState
import com.yvkalume.gifapp.ui.screen.favorite.FavoriteRoute
import com.yvkalume.gifapp.ui.screen.home.HomeRoute
import com.yvkalume.gifapp.ui.util.Destination
import com.yvkalume.gifapp.ui.util.isCurrent


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RootScreen(modifier: Modifier = Modifier) {
    val navController = rememberNavController()
    val appState = rememberAppState(navController = navController)

    Scaffold(
        modifier = modifier,
        topBar = {
            if (appState.currentTopAppBarTitle != null) {
                Surface(shadowElevation = 2.dp) {
                    CenterAlignedTopAppBar(
                        title = {
                            Text(
                                text = appState.currentTopAppBarTitle.toString(),
                                style = MaterialTheme.typography.titleMedium,
                            )
                        }
                    )
                }
            }
        },
        bottomBar = {
            NavigationBar {
                appState.topLevelDestination.forEach { destination ->
                    val currentDestination = appState.currentDestination
                    NavigationBarItem(
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
                                    MaterialTheme.colorScheme.primary
                                } else {
                                    MaterialTheme.colorScheme.onSurface
                                }
                            )
                        },
                        label = {
                            Text(
                                text = stringResource(id = destination.label),
                                color = if (currentDestination.isCurrent(destination.route)) {
                                    MaterialTheme.colorScheme.primary
                                } else {
                                    MaterialTheme.colorScheme.onSurface
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