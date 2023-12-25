@file:OptIn(ExperimentalMaterial3Api::class)

package com.ahmed.banaotask.core.design.drawer

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.NavigationDrawerItem
import androidx.compose.material3.NavigationDrawerItemDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.ahmed.banaotask.core.navigation.Screen
import kotlinx.coroutines.launch

@Composable
fun DrawerContent(
    navHostController: NavHostController,
    content: @Composable (contentPadding: PaddingValues) -> Unit
) {
    val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
    val scope = rememberCoroutineScope()
    ModalNavigationDrawer(
        drawerContent = {
            ModalDrawerSheet(Modifier.fillMaxWidth(.8f)) {
                Spacer(modifier = Modifier.height(20.dp))
                NavigationDrawerItem(label = { Text(text = "Home") }, selected = true, badge = {
                    Icon(Icons.Default.Home, contentDescription = null)
                }, onClick = {
                    navHostController.navigate(Screen.HomeScreen.routeName)
                    scope.launch { drawerState.close() }
                }, modifier = Modifier.padding(NavigationDrawerItemDefaults.ItemPadding)
                )
                Spacer(modifier = Modifier.height(10.dp))
                NavigationDrawerItem(label = { Text(text = "Search") }, selected = true, badge = {
                    Icon(Icons.Default.Search, contentDescription = null)
                }, onClick = {
                    navHostController.navigate(Screen.SearchScreen.routeName)
                    scope.launch { drawerState.close() }
                }, modifier = Modifier.padding(NavigationDrawerItemDefaults.ItemPadding)
                )
            }
        }, drawerState = drawerState
    ) {
        Scaffold(topBar = {
            TopAppBar(title = { Text(text = "Banao Task 1") }, navigationIcon = {
                IconButton(onClick = {
                    scope.launch {
                        drawerState.apply {
                            if (isClosed) open() else close()
                        }
                    }
                }) {
                    Icon(Icons.Default.Menu, contentDescription = null)
                }
            })
        }) { contentPadding ->
            content(contentPadding)
        }
    }
}


