package com.example.testbd.ui

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Menu
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.testbd.ui.common.FloatingBtn
import com.example.testbd.ui.navigation.NavItem
import com.example.testbd.ui.navigation.Navigation
import com.example.testbd.ui.theme.TestBDTheme

@ExperimentalMaterialApi
@ExperimentalFoundationApi
@Composable
fun AppTestBd() {

    val appState = rememberAppTestBdState()

    ScreenTestBd {
        Scaffold(
            topBar = {
                TopAppBar(
                    title = { Text(text = "AppTestBD") },
                    navigationIcon = {
                        if (appState.showUpNavigation) {
                            IconButton(
                                onClick = { appState.onUpClick() }
                            ) {
                                Icon(
                                    imageVector = Icons.Default.ArrowBack,
                                    contentDescription = "Go to previous Screen"
                                )
                            }
                        }
                    }
                )
            },
            floatingActionButton = {
                /**
                 * si la pantalla actual es la home, muestra floatingbutton
                 */
                if(appState.showFloatingButton) {
                    FloatingBtn { appState.onNavItemClick(NavItem.NEWPLAYER) }
                }
            },
            scaffoldState = rememberScaffoldState()
        ) { padding ->
            Box(modifier = Modifier.padding(padding)) {
                Navigation(appState.navController)
            }
        }
    }
}


@Composable
fun ScreenTestBd(content: @Composable () -> Unit) {
    TestBDTheme() {
        // A surface container using the 'background' color from the theme
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colors.background
        ) {
            content()
        }
    }
}