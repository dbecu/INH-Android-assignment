package nl.becu.dewi.student656552.articles.presentation.fav_screen

import android.content.SharedPreferences
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import nl.becu.dewi.student656552.articles.presentation.main_screen.MainViewModel
import nl.becu.dewi.student656552.articles.presentation.main_screen.components.ArticleList
import nl.becu.dewi.student656552.articles.presentation.main_screen.components.ArticleTab
import nl.becu.dewi.student656552.articles.presentation.screens.DefaultScreen

@Composable
fun FavScreen(
    navController: NavController,
    viewModel: FavViewModel = hiltViewModel()
) {

    DefaultScreen(navController = navController, navigationTitle = "Favorites", haveBackButton = false) {
        Column(
            modifier = Modifier.fillMaxSize()
        ) {
            val state = viewModel.state.value
            viewModel.init()

            for(item in viewModel.state.value.articles)
                ArticleTab(article = item, navController = navController)
        }
    }

}

