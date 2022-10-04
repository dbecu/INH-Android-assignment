package nl.becu.dewi.student656552.articles.presentation.logout_screen

import nl.becu.dewi.student656552.articles.presentation.login_screen.LoginEvent

sealed class LogoutEvent {
    object Logout: LogoutEvent()
}
