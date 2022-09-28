package nl.becu.dewi.student656552.presentation.main_screen

import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import nl.becu.dewi.student656552.presentation.main_screen.components.ArticleList
import nl.becu.dewi.student656552.presentation.screens.DefaultScreen
import nl.becu.dewi.student656552.presentation.viewmodels.NetworkViewModel

@Composable
fun MainScreen(
    navController: NavController
) {
    DefaultScreen() {
        ArticleList(navController = navController)
    }

}