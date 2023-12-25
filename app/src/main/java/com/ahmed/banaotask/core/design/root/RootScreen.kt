package com.ahmed.banaotask.core.design.root

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import com.ahmed.banaotask.core.design.drawer.DrawerContent
import com.ahmed.banaotask.ui.theme.BanaoTaskTheme


@Composable
fun RootScreen(navHostController: NavHostController, content: @Composable () -> Unit) {

    BanaoTaskTheme {

        DrawerContent(navHostController) { contentPadding ->
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(contentPadding)
            ) {
                content()
            }
        }

    }
}