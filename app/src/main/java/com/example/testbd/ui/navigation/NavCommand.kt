package com.example.testbd.ui.navigation

import androidx.annotation.StringRes
import androidx.navigation.NavType
import androidx.navigation.navArgument
import com.example.testbd.R


/**
 * contiene las rutas de cada item de navegacion
 */
enum class NavItem(
    val navCommand: NavCommand,
    @StringRes val typeName: Int
) {
    PLAYERS(NavCommand.ContentType(Feature.PLAYERS), R.string.players),
    NEWPLAYER(NavCommand.ContentType(Feature.NEWPLAYER), R.string.newplayer),
    EDIT(NavCommand.ContentEdit(Feature.EDIT), R.string.edit)
}


/**
 * determina los comandos de navegacion
 */
sealed class NavCommand(
    internal val feature: Feature,
    internal val subRoute: String = "home",
    private val navArgs: List<NavArg> = emptyList() // lista de argumentos de navegacion
) {

    class ContentType(feature: Feature): NavCommand(feature)

    class ContentEdit(feature: Feature): NavCommand(feature, "edit", listOf(NavArg.PlayerId)) {

        fun createNavRoute(playerId: Int) = "${feature.route}/$subRoute/$playerId"
    }


    /**
     * - se encargara de crear la ruta del tipo:
     *      baseRoute/{arg1}/{arg2}/{arg3}..etc
     * - usamos 'run', xq como es un poco compleja la funcion
     *      queda mas legible entre llaves el codigo
     */
    val route = run {
        // a cada argumento le mapea las llaves
        val argKeys = navArgs.map { "{${it.key}}" }

        // termina de definir el formato de la ruta y argumentos
        listOf(feature.route, subRoute)
            .plus(argKeys)
            .joinToString("/")
    }

    /** esto simplifica o equivale a lo siguiente:
     *   - listOf(navArgument("playerId") { type = NavType.IntType})
     */
    val args = navArgs.map {
        navArgument(it.key) { type = it.navType }
    }

}

/**
 * ademas de definir el argumento, se define el tipo
 *  - y como puede ser cualquiera, usamos <*>
 */
enum class NavArg(val key: String, val navType: NavType<*>) {
    PlayerId("playerId", NavType.IntType)
}