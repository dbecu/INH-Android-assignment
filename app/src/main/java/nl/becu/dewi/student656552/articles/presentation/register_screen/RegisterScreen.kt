package nl.becu.dewi.student656552.articles.presentation.register_screen

import android.content.SharedPreferences
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.text.ClickableText
import androidx.compose.material.Button
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import nl.becu.dewi.student656552.articles.domain.models.RegisterModel
import nl.becu.dewi.student656552.articles.presentation.login_screen.LoginEvent
import nl.becu.dewi.student656552.articles.presentation.main_screen.MainViewModel
import nl.becu.dewi.student656552.articles.presentation.main_screen.components.ArticleList
import nl.becu.dewi.student656552.articles.presentation.screens.DefaultScreen
import nl.becu.dewi.student656552.articles.presentation.util.Screen

@Composable
fun RegisterScreen(
    navController: NavController,
    viewModel: RegisterViewModel = hiltViewModel(),
    sharedPref: SharedPreferences? = null
) {
    DefaultScreen(navController = navController, sharedPref = sharedPref) {
        Column() {
            var userNameText by remember { mutableStateOf(viewModel.userName.value.text) }
            OutlinedTextField(
                value = userNameText,
                onValueChange = {
                    userNameText = it
                    viewModel.onEvent(RegisterEvent.EnteredUsername(userNameText))
                },
                label = { Text("User name") }
            )

            //Passowrd
            var passwordText by remember { mutableStateOf(viewModel.password.value.text) }
            OutlinedTextField(
                value = passwordText,
                onValueChange = {
                    passwordText = it
                    viewModel.onEvent(RegisterEvent.EnteredPassword(passwordText))
                },
                label = { Text("Password") },
                visualTransformation = PasswordVisualTransformation()
            )

            //BUtton Register
            Button(onClick = {
                viewModel.onEvent(
                    RegisterEvent.Register(
                        viewModel.userName.value.text,
                        viewModel.password.value.text
                    )
                )

                sharedPref?.edit()?.apply {    //TODO: do a check
                    putString("userName", viewModel.userName.value.text)
                    apply()
                }

                navController.navigate(Screen.LoginScreen.route)

            }) {
                Text(text = "Register")
            }

            //Register login
            ClickableText(
                text = AnnotatedString("Login"),
                onClick = {
                    navController.navigate(Screen.LoginScreen.route)
                })
        }
    }
}