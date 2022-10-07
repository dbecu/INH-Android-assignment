package nl.becu.dewi.student656552.articles.presentation.detail_screen

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.FloatingActionButton
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalUriHandler
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import coil.compose.AsyncImage
import nl.becu.dewi.student656552.R
import nl.becu.dewi.student656552.articles.domain.models.Article
import nl.becu.dewi.student656552.articles.presentation.screens.DefaultScreen
import nl.becu.dewi.student656552.articles.presentation.util.SharedPreferencesManager
import java.text.SimpleDateFormat

@Composable
fun DetailScreen(
    viewModel: DetailViewModel = hiltViewModel(),
    navController: NavController,
    articleId: Int?
) {
    LaunchedEffect(articleId) {
        viewModel.init(articleId)
        viewModel.onEvent(DetailEvent.GetAuthToken(SharedPreferencesManager.getAuthToken()))
    }

    val state = viewModel.state.value

    if (state.error == null || state.article != null) {
        DefaultScreen(navController = navController, navigationTitle = "", floatButton = {
           if (!SharedPreferencesManager.getAuthToken().isNullOrBlank()) {
               state.article?.let {
                   FloatingButton(
                       isLiked = it.IsLiked,
                       viewModel = viewModel
                   )
               }
           }
        }) {
            DetailScreenContent(state.article)
        }
    } else {
        DefaultScreen(navController = navController, navigationTitle = "", floatButton = {
            Text(text = viewModel.state.value.error?.asComposableString() ?: stringResource(R.string.error_login))
        }) { }
    }
}

@Composable
private fun FloatingButton(isLiked: Boolean, viewModel: DetailViewModel){
    val state = viewModel.state
    var iconFaved by remember { mutableStateOf(isLiked) }

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
            if (iconFaved) Icons.Default.Favorite else Icons.Default.FavoriteBorder, stringResource(R.string.favorite_icon)
        )

    }
}

@Composable
private fun DetailScreenContent(
    article: Article?) {

    val uriHandler = LocalUriHandler.current
    val dateFormat = SimpleDateFormat("yyyy-mm-dd")

    Column(
        Modifier
            .verticalScroll(rememberScrollState())
            .fillMaxWidth()) {
        article?.let {

            AsyncImage(
                model = it.Image,
                contentDescription = stringResource(R.string.article_image_content_description),
                modifier = Modifier
                    .size(120.dp)
                    .padding(4.dp)
                    .fillMaxWidth(),
                placeholder = painterResource(R.drawable.default_image_thumbnail),
                contentScale = ContentScale.FillBounds
            )
            Text(
                text = it.Title,
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold)

            Spacer(modifier = Modifier.padding(16.dp))
            Text(it.Summary)

            Spacer(modifier = Modifier.padding(16.dp))
            Text(modifier = Modifier.clickable {
                uriHandler.openUri(it.Url)
            },
            text = stringResource(R.string.link_to_article))

            Spacer(modifier = Modifier.padding(16.dp))
            Text(dateFormat.format(it.PublishDate))

            Spacer(modifier = Modifier.padding(16.dp))
            Text(stringResource(R.string.related_content))
            for(article in it.Related){
                Text(modifier = Modifier.clickable {
                    uriHandler.openUri(article)
                },
                    text = article)
            }

            Spacer(modifier = Modifier.padding(16.dp))
            Text(stringResource(R.string.categories))
            for(category in it.Categories){
                Text(category.Name)
            }

        }
    }
}