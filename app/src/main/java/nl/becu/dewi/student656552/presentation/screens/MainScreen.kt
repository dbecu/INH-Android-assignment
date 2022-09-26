package nl.becu.dewi.student656552.presentation.screens

import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import nl.becu.dewi.student656552.presentation.components.ArticleList
import nl.becu.dewi.student656552.presentation.viewmodels.NetworkViewModel

@Composable
fun MainScreen(
    navController: NavController
) {
    DefaultScreen(
        navController = navController) {
        ArticleList(viewModel = NetworkViewModel(), navController = navController)
    }

}