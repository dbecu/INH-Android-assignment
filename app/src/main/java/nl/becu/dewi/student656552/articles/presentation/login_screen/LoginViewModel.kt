package nl.becu.dewi.student656552.articles.presentation.login_screen

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import nl.becu.dewi.student656552.articles.domain.use_case.user_use_case.UserUseCase
import javax.inject.Inject
import nl.becu.dewi.student656552.articles.presentation.login_screen.LoginEvent.Login
import nl.becu.dewi.student656552.articles.presentation.login_screen.LoginEvent.EnteredUsername
import nl.becu.dewi.student656552.articles.presentation.util.SharedPreferencesManager

@HiltViewModel
class LoginViewModel@Inject constructor(
    private val userUseCases: UserUseCase
) : ViewModel() {

    private val _userName = mutableStateOf(
        LoginTextFieldState(
            text = ""
        )
    )
    val userName: State<LoginTextFieldState> = _userName

    private val _password = mutableStateOf(
        LoginTextFieldState(
            text = ""
        )
    )
    val password: State<LoginTextFieldState> = _password

    private val _authToken = mutableStateOf(
        LoginTextFieldState(
            text = ""
        )
    )
    val authToken: State<LoginTextFieldState> = _authToken

    fun onEvent(event: LoginEvent) {
        when (event) {
            is EnteredUsername -> {
                _userName.value = userName.value.copy(
                    text = event.value
                )
            }
            is LoginEvent.EnteredPassword -> {
                _password.value = _password.value.copy(
                    text = event.value
                )
            }
            is Login -> {
                viewModelScope.launch {
                    _authToken.value = authToken.value.copy(
                        text = userUseCases.login(userName.value.text, password.value.text)
                    )

                    SharedPreferencesManager.setUsername(userName.value.text)
                    SharedPreferencesManager.setPassword(password.value.text)
                    SharedPreferencesManager.setAuthToken(authToken.value.text)
                }
            }

        }
    }
}