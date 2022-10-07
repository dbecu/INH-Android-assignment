package nl.becu.dewi.student656552.articles.presentation.logout_screen

import android.content.SharedPreferences
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import dagger.hilt.android.lifecycle.HiltViewModel
import nl.becu.dewi.student656552.R
import nl.becu.dewi.student656552.articles.presentation.login_screen.LoginEvent
import nl.becu.dewi.student656552.articles.presentation.login_screen.LoginScreenContent
import nl.becu.dewi.student656552.articles.presentation.login_screen.LoginViewModel
import nl.becu.dewi.student656552.articles.presentation.screens.DefaultScreen
import nl.becu.dewi.student656552.articles.presentation.util.Screen

@Composable
fun LogoutScreen(
    navController: NavController,
    viewModel: LogoutViewModel = hiltViewModel()
){
    DefaultScreen(navController = navController, haveBackButton = false) {
        //Logout
        Column(
            modifier = Modifier.fillMaxWidth().fillMaxHeight(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Button(
                onClick = {
                    viewModel.onEvent(LogoutEvent.Logout)
                    navController.navigate(Screen.MainScreen.route)
                },
            ) {
                Text(text = stringResource(R.string.logout))
            }
        }
    }
}
