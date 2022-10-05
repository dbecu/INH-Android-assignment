package nl.becu.dewi.student656552.articles.presentation.fav_screen

import android.content.SharedPreferences
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Button
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.paging.LoadState
import androidx.paging.compose.collectAsLazyPagingItems
import androidx.paging.compose.items
import nl.becu.dewi.student656552.articles.presentation.main_screen.MainViewModel
import nl.becu.dewi.student656552.articles.presentation.main_screen.components.ArticleList
import nl.becu.dewi.student656552.articles.presentation.main_screen.components.ArticleTab
import nl.becu.dewi.student656552.articles.presentation.screens.DefaultScreen

@Composable
fun FavScreen(
    navController: NavController,
    viewModel: FavViewModel = hiltViewModel()
) {
    val articlePaging = viewModel.articles.collectAsLazyPagingItems()

    DefaultScreen(navController = navController, navigationTitle = "Favorites", haveBackButton = false) {
        LazyColumn {
            val loading = articlePaging.loadState.prepend is LoadState.Loading
                    || articlePaging.loadState.append is LoadState.Loading
                    || articlePaging.loadState.refresh is LoadState.Loading

            item {
                Button(onClick = {
                    articlePaging.refresh()
                }) {
                    Text("Refresh")
                }
            }


            items(articlePaging) {
                if (it != null && it.IsLiked) {
                    ArticleTab(article = it, navController = navController)
                }
            }

            item {
                if (loading) {
                    CircularProgressIndicator()
                }
            }
        }
    }
}

