package nl.becu.dewi.student656552.articles.presentation.util

import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import nl.becu.dewi.student656552.articles.presentation.detail_screen.DetailScreen
import nl.becu.dewi.student656552.articles.presentation.fav_screen.FavScreen
import nl.becu.dewi.student656552.articles.presentation.login_screen.LoginScreen
import nl.becu.dewi.student656552.articles.presentation.logout_screen.LogoutScreen
import nl.becu.dewi.student656552.articles.presentation.main_screen.MainScreen
import nl.becu.dewi.student656552.articles.presentation.register_screen.RegisterScreen

@Composable
fun Navigation(navController: NavHostController){
    NavHost(navController = navController, startDestination = Screen.MainScreen.route) {
        composable(
            route = Screen.MainScreen.route
        ) {
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
        ) { entry ->
            DetailScreen(
                articleId = entry.arguments?.getInt("articleId"),
                navController = navController
            )
        }

        composable(
            route = Screen.LoginScreen.route
        ) {
            LoginScreen(navController = navController)
        }

        composable(
            route = Screen.LogoutScreen.route
        ) {
            LogoutScreen(navController = navController)
        }

        composable(
            route = Screen.RegisterScreen.route
        ) {
            RegisterScreen(navController = navController)
        }

        composable(
            route = Screen.FavScreen.route
        ) {
            FavScreen(navController = navController)
        }
    }
}