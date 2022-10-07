package nl.becu.dewi.student656552.articles.presentation.fav_screen

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.paging.LoadState
import androidx.paging.compose.collectAsLazyPagingItems
import androidx.paging.compose.items
import nl.becu.dewi.student656552.R
import nl.becu.dewi.student656552.articles.presentation.main_screen.components.ArticleTab
import nl.becu.dewi.student656552.articles.presentation.screens.DefaultScreen

@Composable
fun FavScreen(
    navController: NavController,
    viewModel: FavViewModel = hiltViewModel()
) {
    val articlePaging = viewModel.articles.collectAsLazyPagingItems()
    val error = articlePaging.loadState.prepend is LoadState.Error
            || articlePaging.loadState.refresh is LoadState.Error
            || articlePaging.loadState.append is LoadState.Error

    DefaultScreen(navController = navController, navigationTitle = stringResource(R.string.favorites), haveBackButton = false) {
        LazyColumn(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally) {


            item {
                Button(onClick = {
                    articlePaging.refresh()
                }) {
                    Text(stringResource(R.string.refresh))
                }
            }

            if (error)
            {
                item {
                    Text(text = viewModel.state.value.error?.asComposableString() ?: stringResource(R.string.error),
                        color = MaterialTheme.colors.onBackground)
                }
            } else if (articlePaging.itemCount <= 0) {
                item{
                    Text(text = stringResource(R.string.no_liked_articles),
                        color = MaterialTheme.colors.onBackground)
                }
            } else {

                if (articlePaging.loadState.prepend is LoadState.Loading || articlePaging.loadState.refresh is LoadState.Loading) {
                    item {
                        LinearProgressIndicator()
                    }
                }
                
                items(articlePaging) {
                    if (it != null) {
                        ArticleTab(article = it, navController = navController)
                    }
                }

                item {
                    if (articlePaging.loadState.append is LoadState.Loading) {
                        CircularProgressIndicator()
                    }
                }

            }
        }
    }
}

