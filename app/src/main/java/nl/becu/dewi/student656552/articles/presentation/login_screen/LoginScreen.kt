package nl.becu.dewi.student656552.articles.presentation.login_screen

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
    val userName = viewModel.userName
    val scaffoldState = rememberScaffoldState()
    val scope = rememberCoroutineScope()

    DefaultScreen(navController = navController) {
        LoginScreenContent(viewModel, navController)
    }
}

@Composable
fun LoginScreenContent(viewModel: LoginViewModel, navController: NavController){
    Column(Modifier.padding(16.dp)) {
        SimpleOutlinedTextField("User name", viewModel.userName)
        SimpleOutlinedTextField("Password", viewModel.password)
        Button(onClick = {
            viewModel.onEvent(LoginEvent.Login(viewModel.userName.value.text, viewModel.password.value.text))
            navController.navigate(Screen.MainScreen.withOptionalAuthArgs(viewModel.authToken.value.text)) //if all goes good
            //            navController.navigate(Screen.DetailScreen.withArgs(article.Id))
        }) {
            Text(text = "Login")
        }
        ClickableText(
            text = AnnotatedString("Register"),
            onClick = {
                //viewModel.onEvent()
            })
    }

}

@Composable
fun SimpleOutlinedTextField(label: String, state: State<LoginTextFieldState>, isPassword: Boolean = false) {
    var text by remember { mutableStateOf(state.value.text) }

    OutlinedTextField(
        value = text,
        onValueChange = { text = it },
        label = { Text(label) },
        visualTransformation = if (isPassword) { PasswordVisualTransformation() } else {
            VisualTransformation.None
        },
    )
}