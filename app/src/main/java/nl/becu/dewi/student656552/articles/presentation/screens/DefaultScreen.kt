package nl.becu.dewi.student656552.articles.presentation.screens

import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.navigation.NavController

@Composable
fun DefaultScreen(
    navController: NavController?,
    content: @Composable() () -> Unit
) {
    Scaffold (
        topBar = {
            TopAppBar(
                title = { Text("Triple newspaper") },
                backgroundColor = MaterialTheme.colors.primary,
                contentColor = MaterialTheme.colors.onPrimary
            )
        },
        content = { content() },
        bottomBar = {
            BottomAppBar(
                backgroundColor = MaterialTheme.colors.primary,
                contentColor = MaterialTheme.colors.onPrimary
            )
            {
                Text("Bottom app bar")
            }
        }
    )
}