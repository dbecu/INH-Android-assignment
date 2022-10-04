package nl.becu.dewi.student656552.articles.presentation.logout_screen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import nl.becu.dewi.student656552.articles.domain.use_case.UserUseCase
import nl.becu.dewi.student656552.articles.presentation.login_screen.LoginEvent
import javax.inject.Inject

@HiltViewModel
class LogoutViewModel @Inject constructor(
    private val userUseCases: UserUseCase
) : ViewModel() {

}
