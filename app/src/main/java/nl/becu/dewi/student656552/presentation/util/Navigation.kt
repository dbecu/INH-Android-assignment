package nl.becu.dewi.student656552.presentation.util

import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import nl.becu.dewi.student656552.presentation.screens.DetailScreen
import nl.becu.dewi.student656552.presentation.screens.MainScreen

@Composable
fun Navigation(){
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = Screen.MainScreen.route) {
        composable(route = Screen.MainScreen.route) {
            MainScreen(navController = navController)
        }

        composable(
            route = Screen.DetailScreen.route + "/{articleId}",
            arguments = listOf(
                navArgument("articleId") {
                    type = NavType.IntType
                    nullable = false
                }
            )
        ) {
            entry ->
            DetailScreen(articleId = entry.arguments?.getInt("articleId"), navController = null)
        }
    }
}