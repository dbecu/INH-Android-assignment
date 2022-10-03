package nl.becu.dewi.student656552.articles.presentation.screens

import android.util.Log.d
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.painterResource
import androidx.navigation.NavController

@Composable
fun DefaultScreen(
    navigationTitle: String = "Triple newspaper",
    haveBackButton: Boolean = true,
    navController: NavController,
    content: @Composable() () -> Unit,
) {
    Scaffold (
        topBar = {
            TopAppBar(
                title = { Text(navigationTitle) },
                backgroundColor = MaterialTheme.colors.primary,
                contentColor = MaterialTheme.colors.onPrimary,
                navigationIcon = {
                    if (haveBackButton) {
                        IconButton(onClick = { navController.popBackStack() }) {
                            //TODO: Add an icon
                            Text("Back")
                        }
                    }
                }
            )
        },
        content = { content() }
    )
}