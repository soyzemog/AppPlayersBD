package com.example.testbd.ui

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.testbd.ui.navigation.NavItem
import com.example.testbd.ui.navigation.navigatePoppingUpToStartDestination

@Composable
fun rememberAppTestBdState(
    navController: NavHostController = rememberNavController()
): AppTestBdState = remember(navController) {
    AppTestBdState(navController)
}


class AppTestBdState(val navController: NavHostController) {

    // Constantes
    companion object {
    }


    // Properties

    /** BackStackEntry
     * es el q permite acceder a la ruta actual
     * Y necesita de una funcion composable para poder usarse (@Composable get()) **/

    /** si viene alguna ruta vacia, devolvemos cadena vacia **/
    val currentRoute: String
        @Composable get() = navController.currentBackStackEntryAsState().value?.destination?.route ?: ""


    /**
     * muestra boton arrowback si esta en pantalla newplayer o edit
     * (son misma pantalla distinta ruta)
     */
     val showUpNavigation: Boolean
        @Composable get() = (
                currentRoute == NavItem.NEWPLAYER.navCommand.route ||
                        currentRoute == NavItem.EDIT.navCommand.route
                )

    val showFloatingButton: Boolean
        @Composable get() = currentRoute == NavItem.PLAYERS.navCommand.route


    // Logica de UI
    fun onUpClick() {
        navController.popBackStack()
    }


    fun onNavItemClick(navItem: NavItem) {
        navController.navigatePoppingUpToStartDestination(navItem.navCommand.route)
    }

}