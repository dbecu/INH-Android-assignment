package nl.becu.dewi.student656552.articles.presentation.detail_screen

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalUriHandler
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import coil.compose.AsyncImage
import nl.becu.dewi.student656552.R
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
    val uriHandler = LocalUriHandler.current

    Column() {
        state.article?.let {
            AsyncImage(
                model = it.Image,
                contentDescription = "Image of news item",
                modifier = Modifier
                    .size(64.dp)
                    .padding(4.dp),
                placeholder = painterResource(R.drawable.default_image_thumbnail)
            )
            Text(it.Title)
            Text(it.Summary)
            Text(modifier = Modifier.clickable {
                uriHandler.openUri(it.Url) //TODO: What does it mean, must reside behind a clickable view?
            },
            text = "ExampleText")
        }
    }
}