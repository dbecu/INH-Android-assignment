package nl.becu.dewi.student656552.articles.presentation.main_screen.components

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.paging.LoadState
import androidx.paging.compose.collectAsLazyPagingItems
import androidx.paging.compose.items
import nl.becu.dewi.student656552.R
import nl.becu.dewi.student656552.articles.presentation.main_screen.MainViewModel

@Composable
fun ArticleList(
    viewModel: MainViewModel = hiltViewModel(),
    navController: NavController
) {
    val articlePaging = viewModel.articles.collectAsLazyPagingItems()
    articlePaging

    val error = articlePaging.loadState.prepend is LoadState.Error
            || articlePaging.loadState.refresh is LoadState.Error
            || articlePaging.loadState.append is LoadState.Error

    LazyColumn(modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally) {

        item {
            Button(onClick = {
                articlePaging.refresh()
            }) {
                Text(stringResource(R.string.refresh))
            }
        }

        if (error || viewModel.state.value.error != null)
        {
            item {
                Text(text = viewModel.state.value.error?.asComposableString() ?: stringResource(R.string.error),
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