package nl.becu.dewi.student656552.articles.presentation.util

import android.content.Context
import android.content.SharedPreferences
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf

object SharedPreferencesManager {
    private lateinit var sharedPref: SharedPreferences
    val authTokenState: MutableState<String> = mutableStateOf("") //TODO make this private and have a public authtoken
    private lateinit var _context: Context

    private val _authToken = "authToken"
    private val _username = "username"
    private val _password = "password"

    fun init(context: Context){
        sharedPref = context.getSharedPreferences("mypref", Context.MODE_PRIVATE)
        authTokenState.value = sharedPref.getString("authToken", "").toString()
        _context = context
    }

    fun getContext() : Context {
        return _context
    }

    fun setAuthToken(authToken: String?) {
        val editor = sharedPref.edit()
        editor.apply{
            putString(_authToken, authToken)
        }.apply()
        authTokenState.value = authToken ?: ""
    }

    fun getAuthToken(): String? {
        return sharedPref.getString(_authToken, null)  //TODO chane to null
    }

    fun setUsername(username: String) {
        val editor = sharedPref.edit()
        editor.apply{
            putString(_username, username)
        }.apply()
    }

    fun getUsername(): String? {
        return sharedPref.getString(_username, null)  //TODO chane to null
    }

    fun setPassword(password: String) {
        val editor = sharedPref.edit()
        editor.apply{
            putString(_password, password)
        }.apply()
    }

    fun getPassword(): String? {
        return sharedPref.getString(_password, null)  //TODO chane to null
    }
}