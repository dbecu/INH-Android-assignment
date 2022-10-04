package nl.becu.dewi.student656552.articles.presentation.register_screen

sealed class RegisterEvent{
    data class EnteredUsername(val value: String): RegisterEvent()
    data class EnteredPassword(val value: String): RegisterEvent()
    data class Register(val userName: String, val password: String): RegisterEvent()
}