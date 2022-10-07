package nl.becu.dewi.student656552.articles.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.ui.Modifier
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import dagger.hilt.android.AndroidEntryPoint
import nl.becu.dewi.student656552.articles.presentation.detail_screen.DetailScreen
import nl.becu.dewi.student656552.articles.presentation.fav_screen.FavScreen
import nl.becu.dewi.student656552.articles.presentation.login_screen.LoginScreen
import nl.becu.dewi.student656552.articles.presentation.logout_screen.LogoutScreen
import nl.becu.dewi.student656552.articles.presentation.main_screen.MainScreen
import nl.becu.dewi.student656552.articles.presentation.register_screen.RegisterScreen
import nl.becu.dewi.student656552.articles.presentation.util.Navigation
import nl.becu.dewi.student656552.articles.presentation.util.Screen
import nl.becu.dewi.student656552.articles.presentation.util.SharedPreferencesManager
import nl.becu.dewi.student656552.ui.theme.Student656552Theme

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    @ExperimentalAnimationApi
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Student656552Theme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {


                    SharedPreferencesManager.init(this)

                    val navController = rememberNavController()
                    Navigation(navController)
                }
            }
        }
    }
}