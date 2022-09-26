package nl.becu.dewi.student656552.presentation.components

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.paging.LoadState

@Composable
fun PageState(
    state: LoadState,
    modifier: Modifier = Modifier
) {
    when (state) {
        is LoadState.Loading -> {}
        is LoadState.Error -> {}
        is LoadState.NotLoading -> {}
    }
}