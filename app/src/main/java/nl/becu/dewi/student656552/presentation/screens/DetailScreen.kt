package nl.becu.dewi.student656552.presentation.screens

import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController

@Composable
fun DetailScreen(
    navController: NavController?,
    articleId: Int?) {
    DefaultScreen(navController = navController) {
        DetailScreenContent()
    }}

@Preview(showBackground = true)
@Composable
private fun DetailScreenContent(){

}