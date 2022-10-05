package nl.becu.dewi.student656552.articles.presentation.detail_screen

import android.content.SharedPreferences
import android.graphics.fonts.FontStyle
import androidx.compose.foundation.clickable
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.FloatingActionButton
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.runtime.*
import androidx.compose.ui.text.font.FontStyle.Companion.Italic
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalUriHandler
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontStyle.Companion.Italic
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import coil.compose.AsyncImage
import nl.becu.dewi.student656552.R
import nl.becu.dewi.student656552.articles.domain.models.Article
import nl.becu.dewi.student656552.articles.presentation.main_screen.MainViewModel
import nl.becu.dewi.student656552.articles.presentation.screens.DefaultScreen
import nl.becu.dewi.student656552.articles.presentation.util.Screen
import nl.becu.dewi.student656552.articles.presentation.util.SharedPreferencesManager
import java.text.DateFormat
import java.text.SimpleDateFormat
import java.time.format.DateTimeFormatter

@Composable
fun DetailScreen(
    viewModel: DetailViewModel = hiltViewModel(),
    navController: NavController,
    articleId: Int?
) {

    if (articleId == null)
    {
        navController.navigate(route = Screen.MainScreen.route)
        return
    }

    LaunchedEffect(articleId) {
        viewModel.init(articleId)
        viewModel.onEvent(DetailEvent.GetAuthToken(SharedPreferencesManager.getAuthToken()))
    }

    val state = viewModel.state.value
    state.article?.Title?.let {
        DefaultScreen(navController = navController, navigationTitle = it, floatButton = {
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
    var iconFaved by remember {
        mutableStateOf(isLiked)
    }

    FloatingActionButton(
        onClick = {
            if (iconFaved) {
                viewModel.onEvent(DetailEvent.DeleteArticleLike(state.value.article?.Id ?: 1, state.value.authToken ?: "")) //TODO change
            } else {
                viewModel.onEvent(DetailEvent.PutArticleLike(state.value.article?.Id ?: 1, state.value.authToken ?: "")) //TODO change
            }
            iconFaved = !iconFaved
        }
    ){
        Icon(
            if (iconFaved) Icons.Default.Favorite else Icons.Default.FavoriteBorder, ""
        )

    }
}

@Composable
private fun DetailScreenContent(
    article: Article?) {

    val uriHandler = LocalUriHandler.current
    val dateFormat = SimpleDateFormat("yyyy-mm-dd")

    Column(Modifier.verticalScroll(rememberScrollState()).fillMaxWidth()) {
        article?.let {

            Row() {
                AsyncImage(
                    model = it.Image,
                    contentDescription = "Image of news item",
                    modifier = Modifier
                        .size(120.dp)
                        .padding(4.dp),
                    placeholder = painterResource(R.drawable.default_image_thumbnail)
                )
                Text(
                    text = it.Title,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold)
            }

            Spacer(modifier = Modifier.padding(16.dp))
            Text(it.Summary)

            Spacer(modifier = Modifier.padding(16.dp))
            Text(modifier = Modifier.clickable {
                uriHandler.openUri(it.Url) //TODO: What does it mean, must reside behind a clickable view?
            },
            text = "Link to article")

            Spacer(modifier = Modifier.padding(16.dp))
            Text(dateFormat.format(it.PublishDate))

            Spacer(modifier = Modifier.padding(16.dp))
            Text("Related content")
            for(article in it.Related){
                Text(modifier = Modifier.clickable {
                    uriHandler.openUri(article)
                },
                    text = article)
            }

            Spacer(modifier = Modifier.padding(16.dp))
            Text("Categories")
            for(category in it.Categories){
                Text(category.Name)
            }

        }
    }
}