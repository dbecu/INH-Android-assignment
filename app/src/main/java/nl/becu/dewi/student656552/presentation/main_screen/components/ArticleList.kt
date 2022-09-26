package nl.becu.dewi.student656552.presentation.main_screen.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import androidx.paging.compose.collectAsLazyPagingItems
import androidx.paging.compose.items
import nl.becu.dewi.student656552.presentation.components.PageState
import nl.becu.dewi.student656552.presentation.viewmodels.NetworkViewModel

@Composable
fun ArticleList(
    viewModel: NetworkViewModel = androidx.lifecycle.viewmodel.compose.viewModel(),
    navController: NavController
){
    val articlePaging = viewModel.articles.collectAsLazyPagingItems()
    val state = articlePaging.loadState

    Box{
        LazyColumn {
            item { PageState(state.prepend) }

            items(articlePaging) {
                if (it != null)
                {
                    ArticleTab(it, navController)
                }
            }
            item { PageState(state = state.append) }
        }
        PageState(state = state.refresh)
    }
}