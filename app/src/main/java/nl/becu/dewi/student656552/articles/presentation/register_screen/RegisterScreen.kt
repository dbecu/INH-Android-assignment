package nl.becu.dewi.student656552.articles.presentation.register_screen

import android.content.SharedPreferences
import android.widget.Toast
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.ClickableText
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
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
import nl.becu.dewi.student656552.articles.domain.models.RegisterModel
import nl.becu.dewi.student656552.articles.presentation.login_screen.LoginEvent
import nl.becu.dewi.student656552.articles.presentation.main_screen.MainViewModel
import nl.becu.dewi.student656552.articles.presentation.main_screen.components.ArticleList
import nl.becu.dewi.student656552.articles.presentation.screens.DefaultScreen
import nl.becu.dewi.student656552.articles.presentation.util.Screen
import nl.becu.dewi.student656552.articles.presentation.util.SharedPreferencesManager

@Composable
fun RegisterScreen(
    navController: NavController,
    viewModel: RegisterViewModel = hiltViewModel()
) {
    DefaultScreen(navController = navController, haveBackButton = false, navigationTitle = stringResource(R.string.register)) {
        Column(Modifier.padding(16.dp)) {
            var userNameText by remember { mutableStateOf(viewModel.userName.value.text) }
            OutlinedTextField(
                value = userNameText,
                onValueChange = {
                    userNameText = it
                    viewModel.onEvent(RegisterEvent.EnteredUsername(userNameText))
                },
                label = { Text(stringResource(R.string.user_name)) }
            )

            //Passowrd
            var passwordText by remember { mutableStateOf(viewModel.password.value.text) }
            OutlinedTextField(
                value = passwordText,
                onValueChange = {
                    passwordText = it
                    viewModel.onEvent(RegisterEvent.EnteredPassword(passwordText))
                },
                label = { Text(stringResource(R.string.password)) },
                visualTransformation = PasswordVisualTransformation()
            )

            val ctx = LocalContext.current
            var isClicked = remember { mutableStateOf(0) }

            //BUtton Register
            Button(onClick = {
                viewModel.onEvent(
                    RegisterEvent.Register(
                        viewModel.userName.value.text,
                        viewModel.password.value.text
                    )
                )
                isClicked.value = isClicked.value + 1

                if (SharedPreferencesManager.getUsername() != "") {
                    navController.navigate(Screen.LoginScreen.route)
                    Toast.makeText(ctx, SharedPreferencesManager.getContext().resources.getString(R.string.success_register), Toast.LENGTH_SHORT).show()
                } else {
                    Toast.makeText(ctx, SharedPreferencesManager.getContext().resources.getString(R.string.error_register), Toast.LENGTH_SHORT).show()
                }

            }) {
                Text(text = stringResource(R.string.register))
            }

            if(SharedPreferencesManager.getUsername().isNullOrBlank()){
                if (isClicked.value == 1)
                {
                    Text(text = viewModel.error.value.uiText?.asComposableString() ?: stringResource(R.string.error_register))
                } else if (isClicked.value > 1)
                {
                    Text(text = stringResource(R.string.are_you_sure))
                }

            }

            //Register login
            ClickableText(
                text = AnnotatedString(stringResource(R.string.login)),
                onClick = {
                    navController.navigate(Screen.LoginScreen.route)
                },
                style = TextStyle(
                    color = MaterialTheme.colors.onBackground))
        }
    }
}