package com.ahmed.banaotask.core.navigation

sealed class Screen(val routeName: String) {
    data object HomeScreen : Screen(routeName = "/home")
    data object SearchScreen : Screen(routeName = "/search")
}