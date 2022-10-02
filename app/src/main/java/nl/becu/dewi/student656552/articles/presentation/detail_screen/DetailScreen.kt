package nl.becu.dewi.student656552.articles.presentation.detail_screen

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import nl.becu.dewi.student656552.articles.presentation.screens.DefaultScreen

@Composable
fun DetailScreen(
    navController: NavController?,
    articleId: Int?) {
    DefaultScreen() {
        DetailScreenContent()
    }
}

@Preview(showBackground = true)
@Composable
private fun DetailScreenContent(){

}