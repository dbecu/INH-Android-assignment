package nl.becu.dewi.student656552.articles.presentation.main_screen.components

import android.content.SharedPreferences
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.paging.LoadState
import androidx.paging.compose.collectAsLazyPagingItems
import androidx.paging.compose.items
import com.google.accompanist.swiperefresh.SwipeRefresh
import com.google.accompanist.swiperefresh.rememberSwipeRefreshState
import nl.becu.dewi.student656552.R
import nl.becu.dewi.student656552.articles.presentation.components.PageState
import nl.becu.dewi.student656552.articles.presentation.main_screen.MainViewModel
import nl.becu.dewi.student656552.articles.presentation.screens.DefaultScreen

@Composable
fun ArticleList(
    viewModel: MainViewModel = hiltViewModel(),
    navController: NavController
) {
    val articlePaging = viewModel.articles.collectAsLazyPagingItems()

    LazyColumn(modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally) {

        if (articlePaging.loadState.prepend is LoadState.Loading || articlePaging.loadState.refresh is LoadState.Loading){
            item {
                LinearProgressIndicator()
            }
        }

        item {
            Button(onClick = {
                articlePaging.refresh()
            }) {
                Text(stringResource(R.string.refresh))
            }
        }


        items(articlePaging) {
            if (it != null) {
                ArticleTab(article = it, navController = navController)
            }
        }

        item {
            if (articlePaging.loadState.append is LoadState.Loading){
                CircularProgressIndicator()
            }
        }
    }

}