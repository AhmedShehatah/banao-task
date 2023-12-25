package com.ahmed.banaotask.core.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.ahmed.banaotask.ui.features.home.HomeScreen
import com.ahmed.banaotask.ui.features.search.SearchScreen


@Composable
fun AppNavHost(navController: NavHostController) {

    NavHost(navController = navController, startDestination = Screen.HomeScreen.routeName) {
        composable(route = Screen.HomeScreen.routeName) {
            HomeScreen()
        }
        composable(Screen.SearchScreen.routeName) { SearchScreen() }
    }
}