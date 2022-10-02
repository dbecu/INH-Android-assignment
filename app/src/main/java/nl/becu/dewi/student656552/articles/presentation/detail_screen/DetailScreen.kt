package nl.becu.dewi.student656552.articles.presentation.detail_screen

import androidx.compose.foundation.layout.Column
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import nl.becu.dewi.student656552.articles.presentation.main_screen.MainViewModel
import nl.becu.dewi.student656552.articles.presentation.screens.DefaultScreen
import nl.becu.dewi.student656552.articles.presentation.util.Screen

@Composable
fun DetailScreen(
    navController: NavController,
    articleId: Int?) {

    DetailScreenContent(navController = navController, articleId = articleId)
}

@Composable
private fun DetailScreenContent(
    viewModel: DetailViewModel = hiltViewModel(),
    navController: NavController,
    articleId: Int?){

    if (articleId == null)
    {
        navController.navigate(route = Screen.MainScreen.route)
        return
    }

    LaunchedEffect(articleId) {
        viewModel.init(articleId)
    }

    val state = viewModel.state.value

    Column() {
        state.article?.let {
            Text(it.Title)
            Text(it.Summary)
        }
    }
}