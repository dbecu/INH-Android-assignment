package nl.becu.dewi.student656552.articles.presentation.screens

import android.content.SharedPreferences
import android.util.Log.d
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import nl.becu.dewi.student656552.articles.presentation.util.Screen
import nl.becu.dewi.student656552.articles.presentation.util.observeAsState

@Composable
fun DefaultScreen(
    navigationTitle: String = "Triple newspaper",
    haveBackButton: Boolean = true,
    navController: NavController,
    sharedPref: SharedPreferences?,
    scaffoldState: ScaffoldState = rememberScaffoldState(),
    floatButton: (@Composable() () -> Unit)? = null,
    content: @Composable() () -> Unit
) {
    var isLoggedIn = false
    if (sharedPref != null) {
        val token = sharedPref.getString("authToken", null)
        isLoggedIn = !token.isNullOrBlank()
    }

    val lifecycleState = LocalLifecycleOwner.current.lifecycle.observeAsState()
    val state = lifecycleState.value


    Scaffold(
        topBar = { TopBar(navigationTitle, haveBackButton, navController) },
        bottomBar = { BottomBar(navController, isLoggedIn = isLoggedIn) },
        floatingActionButton = { floatButton?.let { it() } },
        scaffoldState = scaffoldState
    ) { innerPadding ->
        Box(modifier = Modifier.padding(innerPadding)) {
            content()
        }
    }
}

@Composable
fun TopBar(navigationTitle: String, haveBackButton: Boolean, navController: NavController) {
    TopAppBar(
        title = { Text(navigationTitle) },
        backgroundColor = MaterialTheme.colors.primary,
        contentColor = MaterialTheme.colors.onPrimary,
        navigationIcon = {
            if (haveBackButton) {
                IconButton(onClick = { navController.popBackStack() }) {
                    Icon(imageVector = Icons.Default.ArrowBack,"")
                }
            }
        }
    )
}

@Composable
fun BottomBar(navController: NavController, isLoggedIn: Boolean = false) {
    val selectedIndex = remember { mutableStateOf(0) }
    BottomNavigation(elevation = 10.dp) {

        BottomNavigationItem(icon = {
            Icon(imageVector = Icons.Default.Home,"")
        },
            label = { Text(text = "Home") },
            selected = (selectedIndex.value == 0),
            onClick = {
                selectedIndex.value = 0
                navController.navigate(route = Screen.MainScreen.route)
            })

        BottomNavigationItem(icon = {
            Icon(imageVector = Icons.Default.Person,"")
        },
            label = { Text(text = "Profile") },
            selected = (selectedIndex.value == 1),
            onClick = {
                selectedIndex.value = 1
                if (isLoggedIn) {
                    navController.navigate(route = Screen.LogoutScreen.route)
                } else {
                    navController.navigate(route = Screen.LoginScreen.route)
                }
            })

        if (isLoggedIn) {
            BottomNavigationItem(icon = {
                Icon(imageVector = Icons.Default.Favorite, "")
            },
                label = { Text(text = "Favorites") },
                selected = (selectedIndex.value == 2),
                onClick = {
                    selectedIndex.value = 2
                    navController.navigate(route = Screen.FavScreen.route)
                })
        }
    }
}