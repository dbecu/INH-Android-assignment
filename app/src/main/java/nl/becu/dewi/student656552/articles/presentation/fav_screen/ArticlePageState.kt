package nl.becu.dewi.student656552.articles.presentation.fav_screen

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.paging.LoadState

@Composable
private fun ArticlePageState(
    state: LoadState,
    modifier: Modifier
) {
    when (state) {
        is LoadState.Error -> TODO()
        is LoadState.Loading -> TODO()
        is LoadState.NotLoading -> TODO()
    }
}