package com.example.testbd.ui.navigation

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.runtime.Composable
import androidx.navigation.*
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.testbd.ui.edit.NewPlayerScreen
import com.example.testbd.ui.home.PlayersScreen

@ExperimentalMaterialApi
@ExperimentalFoundationApi
@Composable
fun Navigation(navController: NavHostController) {
    NavHost(
        navController = navController,
        startDestination = Feature.PLAYERS.route
    ) {
        // grafo de navegacion
        playersNav(navController)
        newPlayerNav(navController)
    }
}


@ExperimentalMaterialApi
@ExperimentalFoundationApi
private fun NavGraphBuilder.playersNav(navController: NavHostController) {
    navigation(
        startDestination = NavCommand.ContentType(Feature.PLAYERS).route,
        route = Feature.PLAYERS.route
    ) {

        composable(NavCommand.ContentType(Feature.PLAYERS)) {

            PlayersScreen(
                onEditPlayerClick = { player ->
                    navController.navigate(
                        NavCommand.ContentEdit(Feature.EDIT).createNavRoute(player.id)
                    )
                }
            )

        }

        composable(
            route = NavCommand.ContentEdit(Feature.EDIT).route,
            arguments = listOf(
                navArgument("playerId") {
                    type = NavType.IntType
                }
            )
        ) {
            NewPlayerScreen()
        }

    }
}


@ExperimentalMaterialApi
@ExperimentalFoundationApi
private fun NavGraphBuilder.newPlayerNav(navController: NavHostController) {
    navigation(
        startDestination = NavCommand.ContentType(Feature.NEWPLAYER).route,
        route = Feature.NEWPLAYER.route
    ) {

        composable(NavCommand.ContentType(Feature.NEWPLAYER)) {
            NewPlayerScreen()
        }

    }
}


private fun NavGraphBuilder.composable(
    navCommand: NavCommand,
    content: @Composable (NavBackStackEntry) -> Unit
) {
    composable(
        route = navCommand.route,
        arguments = navCommand.args
    ) {
        content(it)
    }
}