@file:OptIn(ExperimentalMaterial3Api::class)

package com.ahmed.banaotask

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.DismissibleNavigationDrawer
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.NavigationDrawerItem
import androidx.compose.material3.NavigationDrawerItemDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.ahmed.banaotask.ui.features.drawer.DrawerContent
import com.ahmed.banaotask.ui.features.home.HomeScreen
import com.ahmed.banaotask.ui.theme.BanaoTaskTheme
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    @OptIn(ExperimentalGlideComposeApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
            val scope = rememberCoroutineScope()
            BanaoTaskTheme {
                ModalNavigationDrawer(
                    drawerContent = {
                        ModalDrawerSheet(Modifier.fillMaxWidth(.8f)) {
                            Spacer(modifier = Modifier.height(20.dp))
                            NavigationDrawerItem(
                                label = { Text(text = "Home") },
                                selected = true,
                                badge = {
                                    Icon(Icons.Default.Home, contentDescription = null)
                                },
                                onClick = { scope.launch { drawerState.close() } },
                                modifier = Modifier.padding(NavigationDrawerItemDefaults.ItemPadding)
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

                        Box(Modifier.padding(contentPadding)) {
                            HomeScreen()
                        }
                    }

                }


            }
        }
    }
}

