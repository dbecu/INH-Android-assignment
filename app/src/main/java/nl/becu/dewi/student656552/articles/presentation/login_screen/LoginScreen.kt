package nl.becu.dewi.student656552.articles.presentation.login_screen

import android.content.SharedPreferences
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.ClickableText
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import nl.becu.dewi.student656552.articles.presentation.main_screen.MainViewModel
import nl.becu.dewi.student656552.articles.presentation.screens.DefaultScreen
import nl.becu.dewi.student656552.articles.presentation.util.Screen

@Composable
fun LoginScreen(
    navController: NavController,
    viewModel: LoginViewModel = hiltViewModel()
){
    DefaultScreen(navController = navController, haveBackButton = false) {
        LoginScreenContent(viewModel, navController)
    }
}

@Composable
fun LoginScreenContent(viewModel: LoginViewModel, navController: NavController){
    Column(Modifier.padding(16.dp)) {

        //User name
        var userNameText by remember { mutableStateOf(viewModel.userName.value.text) }
        OutlinedTextField(
            value = userNameText,
            onValueChange = {
                userNameText = it
                viewModel.onEvent(LoginEvent.EnteredUsername(userNameText)) },
            label = { Text("User name") }
        )

        //Passowrd
        var passwordText by remember { mutableStateOf(viewModel.password.value.text) }
        OutlinedTextField(
            value = passwordText,
            onValueChange = {
                passwordText = it
                viewModel.onEvent(LoginEvent.EnteredPassword(passwordText)) },
            label = { Text("Password") },
            visualTransformation = PasswordVisualTransformation()
        )

        //BUtton login
        Button(onClick = {
            viewModel.onEvent(LoginEvent.Login(viewModel.userName.value.text, viewModel.password.value.text))
            navController.navigate(Screen.MainScreen.withOptionalAuthArgs(viewModel.authToken.value.text)) //if all goes good
            //            navController.navigate(Screen.DetailScreen.withArgs(article.Id))
        }) {
            Text(text = "Login")
        }

        //Register redirect
        ClickableText(
            text = AnnotatedString("Register"),
            onClick = {
                navController.navigate(Screen.RegisterScreen.route)
            })
    }

}
