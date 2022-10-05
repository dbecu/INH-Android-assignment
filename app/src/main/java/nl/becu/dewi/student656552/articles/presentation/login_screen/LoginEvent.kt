package nl.becu.dewi.student656552.articles.presentation.login_screen

import android.content.SharedPreferences
import androidx.compose.ui.focus.FocusState
import nl.becu.dewi.student656552.articles.domain.models.RegisterModel

sealed class LoginEvent{
    data class EnteredUsername(val value: String): LoginEvent()
    data class EnteredPassword(val value: String): LoginEvent()
    data class Login(val userName: String, val password: String): LoginEvent()
}
