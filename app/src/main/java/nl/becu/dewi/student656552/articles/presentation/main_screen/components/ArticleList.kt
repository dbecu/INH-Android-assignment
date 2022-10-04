package nl.becu.dewi.student656552.articles.presentation.main_screen.components

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Text
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import nl.becu.dewi.student656552.articles.presentation.components.PageState
import nl.becu.dewi.student656552.articles.presentation.main_screen.MainViewModel

@Composable
fun ArticleList(
    viewModel: MainViewModel = hiltViewModel(),
    navController: NavController
) {

    val state = viewModel.state.value
    val scaffoldState = rememberScaffoldState()
    val scope = rememberCoroutineScope()

    LazyColumn(
        modifier = Modifier.fillMaxSize()
    ) {
        items(state.articles.size) { i ->
            val item = state.articles[i]
            if (i >= state.articles.size - 1 &&
                !state.endReached &&
                !state.isLoading) {
                viewModel.loadNextItems()
            }

            ArticleTab(article = item, navController = navController)
        }

        item{
            if (state.isLoading) {
                Row (
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(8.dp),
                    horizontalArrangement = Arrangement.Center
                ) {
                    CircularProgressIndicator()
                }
            }
        }
    }
}