package nl.becu.dewi.student656552.articles.presentation.article_pager

import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.paging.LoadState
import nl.becu.dewi.student656552.R

@Composable
private fun ArticlePageState(
    state: LoadState,
    modifier: Modifier? = null
) {
    when (state) {
        is LoadState.Error -> {
            Text(text = stringResource(R.string.error))
        }
        is LoadState.Loading -> {
            Text(text = stringResource(R.string.loading))
        }
        is LoadState.NotLoading -> {
            Text(text = stringResource(R.string.not_loading))
        }
    }
}