package nl.becu.dewi.student656552.articles.presentation.detail_screen

import android.content.SharedPreferences
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.FloatingActionButton
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
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
import nl.becu.dewi.student656552.articles.domain.models.Article
import nl.becu.dewi.student656552.articles.presentation.main_screen.MainViewModel
import nl.becu.dewi.student656552.articles.presentation.screens.DefaultScreen
import nl.becu.dewi.student656552.articles.presentation.util.Screen
import java.text.DateFormat
import java.text.SimpleDateFormat
import java.time.format.DateTimeFormatter

@Composable
fun DetailScreen(
    viewModel: DetailViewModel = hiltViewModel(),
    navController: NavController,
    articleId: Int?,
    sharedPref: SharedPreferences? = null
) {

    if (articleId == null)
    {
        navController.navigate(route = Screen.MainScreen.route)
        return
    }

    LaunchedEffect(articleId) {
        viewModel.init(articleId)
        viewModel.onEvent(DetailEvent.GetAuthToken(sharedPref?.getString("authToken", null) ?: ""))
    }

    val state = viewModel.state.value
    state.article?.Title?.let {
        DefaultScreen(navController = navController, navigationTitle = it, sharedPref = sharedPref, floatButton = {
            FloatingButton(
                isLiked = state.article.IsLiked,
                viewModel = viewModel
            )
        }) {
            DetailScreenContent(state.article)
        }
    }
}

@Composable
private fun FloatingButton(isLiked: Boolean, viewModel: DetailViewModel){
    val state = viewModel.state

    FloatingActionButton(
        onClick = {
            if (isLiked) {
                viewModel.onEvent(DetailEvent.DeleteArticleLike(state.value.article?.Id ?: 1, state.value.authToken))
            } else {
                viewModel.onEvent(DetailEvent.PutArticleLike(state.value.article?.Id ?: 1, state.value.authToken))
          }
        }
    ){
        Icon(if (isLiked) Icons.Default.Favorite else Icons.Default.FavoriteBorder, "")
    }
}

@Composable
private fun DetailScreenContent(
    article: Article?) {

    val uriHandler = LocalUriHandler.current
    val dateFormat = SimpleDateFormat("yyyy-mm-dd")

    Column() {
        article?.let {
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
            text = "Link to article")
            Text(dateFormat.format(it.PublishDate))

            Text("Related content")
            for(article in it.Related){
                Text(modifier = Modifier.clickable {
                    uriHandler.openUri(article)
                },
                    text = article)
            }

            Text("Categories")
            for(category in it.Categories){
                Text(category.Name)
            }

        }
    }
}