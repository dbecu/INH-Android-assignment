package nl.becu.dewi.student656552.articles.presentation.fav_screen

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import nl.becu.dewi.student656552.articles.domain.use_case.ArticleUseCases
import nl.becu.dewi.student656552.articles.domain.util.DefaultPaginator
import nl.becu.dewi.student656552.articles.presentation.login_screen.LoginEvent
import nl.becu.dewi.student656552.articles.presentation.main_screen.MainState
import nl.becu.dewi.student656552.articles.presentation.util.SharedPreferencesManager
import javax.inject.Inject

@HiltViewModel
class FavViewModel @Inject constructor(
    private val articleUseCases: ArticleUseCases
) : ViewModel() {

    private val _state = mutableStateOf(FavState())
    val state: State<FavState> = _state

    fun init() {
        loadNextItems()
    }

    private fun loadNextItems() {
        viewModelScope.launch {
            _state.value = state.value.copy(
                articles = articleUseCases.getLikedArticles.invoke(SharedPreferencesManager.getAuthToken() ?: "")) //TODO change
        }
    }
}