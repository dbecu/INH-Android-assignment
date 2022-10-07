package nl.becu.dewi.student656552.articles.presentation.login_screen

import android.widget.Toast
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.ClickableText
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import nl.becu.dewi.student656552.R
import nl.becu.dewi.student656552.articles.presentation.screens.DefaultScreen
import nl.becu.dewi.student656552.articles.presentation.util.Screen
import nl.becu.dewi.student656552.articles.presentation.util.SharedPreferencesManager
import nl.becu.dewi.student656552.articles.util.UiText

@Composable
fun LoginScreen(
    navController: NavController,
    viewModel: LoginViewModel = hiltViewModel()
){
    DefaultScreen(navController = navController, haveBackButton = false, navigationTitle = stringResource(R.string.login)) {
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
            label = { Text(stringResource(R.string.user_name)) }
        )

        //Passowrd
        var passwordText by remember { mutableStateOf(viewModel.password.value.text) }
        OutlinedTextField(
            value = passwordText,
            onValueChange = {
                passwordText = it
                viewModel.onEvent(LoginEvent.EnteredPassword(passwordText)) },
            label = { Text(stringResource(R.string.password)) },
            visualTransformation = PasswordVisualTransformation()
        )

        val ctx = LocalContext.current

        var isClicked = remember { mutableStateOf(0) }
        //BUtton login
        Button(onClick = {
            viewModel.onEvent(LoginEvent.Login(viewModel.userName.value.text, viewModel.password.value.text))
            isClicked.value = isClicked.value + 1

            if (!SharedPreferencesManager.getAuthToken().isNullOrBlank()) {
                Toast.makeText(ctx, SharedPreferencesManager.getContext().resources.getString(R.string.success_log_in), Toast.LENGTH_SHORT).show()
                navController.navigate(Screen.MainScreen.route)
            } else {
                Toast.makeText(ctx, SharedPreferencesManager.getContext().resources.getString(R.string.error_login), Toast.LENGTH_SHORT).show()
            }
            //            navController.navigate(Screen.DetailScreen.withArgs(article.Id))
        }) {
            Text(text = stringResource(R.string.login))
        }

        if (SharedPreferencesManager.getAuthToken().isNullOrBlank()) {
            if (isClicked.value == 1)
            {
                Text(text = viewModel.error.value.uiText?.asComposableString() ?: stringResource(R.string.error_login))
            } else if (isClicked.value > 1)
            {
                Text(text = stringResource(R.string.are_you_sure))
            }
        }



        //Register redirect
        ClickableText(
            text = AnnotatedString(stringResource(R.string.register)),
            onClick = {
                navController.navigate(Screen.RegisterScreen.route)
            },
            style = TextStyle(
                color = MaterialTheme.colors.onBackground
            )
        )
    }

}
