package nl.becu.dewi.student656552.articles.presentation.main_screen

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import nl.becu.dewi.student656552.articles.domain.use_case.ArticleUseCases
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val articleUseCases: ArticleUseCases
) : ViewModel() {

    private val _state = mutableStateOf(MainState())
    val state: State<MainState> = _state

    init {
        getArticles()
    }

    private fun getArticles() {
        GlobalScope.launch {
            val articles = articleUseCases.getArticles.invoke()
            _state.value = state.value.copy(articles = articles)
        }
    }
}