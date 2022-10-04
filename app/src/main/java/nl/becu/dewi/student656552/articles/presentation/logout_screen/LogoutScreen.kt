package nl.becu.dewi.student656552.articles.presentation.logout_screen

import android.content.SharedPreferences
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import nl.becu.dewi.student656552.articles.presentation.login_screen.LoginEvent
import nl.becu.dewi.student656552.articles.presentation.login_screen.LoginScreenContent
import nl.becu.dewi.student656552.articles.presentation.login_screen.LoginViewModel
import nl.becu.dewi.student656552.articles.presentation.screens.DefaultScreen
import nl.becu.dewi.student656552.articles.presentation.util.Screen

@Composable
fun LogoutScreen(
    navController: NavController,
    sharedPref: SharedPreferences? = null
){
    DefaultScreen(navController = navController, sharedPref = sharedPref, haveBackButton = false) {
        //Logout
        Button(onClick = {
            sharedPref?.edit()?.apply {    //TODO: do a check
                putString("authToken", null)
                apply()
            }

            navController.navigate(Screen.MainScreen.route)  //navController.navigate(Screen.DetailScreen.withArgs(article.Id))
        }) {
            Text(text = "Logout")
        }
    }
}
