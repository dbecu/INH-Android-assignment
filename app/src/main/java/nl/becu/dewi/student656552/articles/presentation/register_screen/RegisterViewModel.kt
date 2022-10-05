package nl.becu.dewi.student656552.articles.presentation.register_screen

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import nl.becu.dewi.student656552.articles.domain.use_case.ArticleUseCases
import nl.becu.dewi.student656552.articles.domain.use_case.UserUseCase
import nl.becu.dewi.student656552.articles.presentation.login_screen.LoginEvent
import nl.becu.dewi.student656552.articles.presentation.login_screen.LoginTextFieldState
import nl.becu.dewi.student656552.articles.presentation.util.SharedPreferencesManager
import javax.inject.Inject

@HiltViewModel
class RegisterViewModel @Inject constructor(
    private val userUseCase: UserUseCase
) : ViewModel() {
    private val _userName = mutableStateOf(
        RegisterState(
        text = "" ))
    val userName: State<RegisterState> = _userName

    private val _password = mutableStateOf(
        RegisterState(
        text = "" ))
    val password: State<RegisterState> = _password

    private val _message = mutableStateOf(
        RegisterState(
        text = "" ))
    val message: State<RegisterState> = _message

    fun onEvent(event: RegisterEvent) {
        when(event) {
            is RegisterEvent.EnteredUsername -> {
                _userName.value = userName.value.copy(
                    text = event.value)
            }
            is RegisterEvent.EnteredPassword -> {
                _password.value = _password.value.copy(
                    text = event.value)
            }
            is RegisterEvent.Register -> {
                viewModelScope.launch {
                    _message.value = message.value.copy(
                        text = userUseCase.register(userName.value.text, password.value.text).Message)
                    SharedPreferencesManager.setUsername(userName.value.text)
                }
            }
        }
    }
}