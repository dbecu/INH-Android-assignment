package nl.becu.dewi.student656552.articles.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import dagger.hilt.android.AndroidEntryPoint
import nl.becu.dewi.student656552.articles.presentation.util.Navigation
import nl.becu.dewi.student656552.articles.presentation.detail_screen.DetailScreen
import nl.becu.dewi.student656552.articles.presentation.main_screen.MainScreen
import nl.becu.dewi.student656552.articles.presentation.util.Screen

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    @ExperimentalAnimationApi
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
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
    }
}