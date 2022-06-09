package com.rulhouse.evgawatcher.presentation.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.RowScope
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.NavDestination
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.rulhouse.evgawatcher.presentation.Screen

@Composable
fun BottomNavigationBar (
    navController: NavController
) {
    Column(Modifier.background(color = MaterialTheme.colors.surface)) {
        Divider(
            color = MaterialTheme.colors.secondary.copy(alpha = 0.2f),
            thickness = 0.5.dp
        )
        BottomNavigation(backgroundColor = MaterialTheme.colors.surface) {
            val navBackStackEntry by navController.currentBackStackEntryAsState()
            val currentDestination = navBackStackEntry?.destination
            val dashTabs = getDashTabs()
            dashTabs.forEach { screen ->
                BottomNavItem(screen, currentDestination, navController)
            }
        }
    }
}

private fun getDashTabs(): List<Screen> {
    return mutableListOf<Screen>().apply {
        add(Screen.FavoriteProductsScreen)
        add(Screen.AllProductsScreenScreen)
    }
}

@Composable
private fun RowScope.BottomNavItem(
    screen: Screen,
    currentDestination: NavDestination?,
    navController: NavController,
) {

    BottomNavigationItem(
//        selectedContentColor = SlackCloneColorProvider.colors.bottomNavSelectedColor,
//        unselectedContentColor = SlackCloneColorProvider.colors.bottomNavUnSelectedColor,
        icon = { Icon(screen.image, contentDescription = null) },
        label = {
            Text(
                stringResource(screen.resourceId),
                maxLines = 1,
//                style = SlackCloneTypography.overline,
            )
        },
        selected = currentDestination?.hierarchy?.any { it.route == screen.route } == true,
        onClick = {
            navigateTab(navController, screen)
        }
    )
}

private fun navigateTab(
    navController: NavController,
    screen: Screen
) {
    navController.navigate(screen.route) {
        popUpTo(navController.graph.findStartDestination().id) {
            saveState = true
        }
        launchSingleTop = true
        restoreState = true
    }
}