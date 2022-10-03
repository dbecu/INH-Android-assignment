package nl.becu.dewi.student656552.articles.presentation.main_screen

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Text
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import nl.becu.dewi.student656552.articles.presentation.main_screen.components.ArticleList
import nl.becu.dewi.student656552.articles.presentation.screens.DefaultScreen
import nl.becu.dewi.student656552.articles.presentation.viewmodels.NetworkViewModel

@Composable
fun MainScreen(
    navController: NavController,
    viewModel: MainViewModel = hiltViewModel(),
    authToken: String? = null
) {
    DefaultScreen(navController = navController, haveBackButton = false) {
        ArticleList(viewModel = viewModel, navController = navController)
    }
}