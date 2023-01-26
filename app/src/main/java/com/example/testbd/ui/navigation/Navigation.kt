package com.example.testbd.ui.navigation

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.runtime.Composable
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navigation
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

        composable(NavCommand.ContentEdit(Feature.EDIT)) { backStackEntry ->
            val id = backStackEntry.arguments?.getInt(NavArg.PlayerId.key)
            requireNotNull(id)

            NewPlayerScreen(id)
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

            /**
             * cuando creo un nuevo player, le envio un cero,
             * si usamos esta pantalla para el edit, si le envio un id existente
             */
            NewPlayerScreen(0)

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