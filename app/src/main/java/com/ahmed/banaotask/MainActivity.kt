package com.ahmed.banaotask

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.navigation.compose.rememberNavController
import com.ahmed.banaotask.core.design.root.RootScreen
import com.ahmed.banaotask.core.navigation.AppNavHost
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val navHostController = rememberNavController()
            RootScreen(navHostController) {
                AppNavHost(navController = navHostController)
            }
        }
    }
}

