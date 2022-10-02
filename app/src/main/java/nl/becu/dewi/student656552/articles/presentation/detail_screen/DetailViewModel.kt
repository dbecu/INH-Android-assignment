package nl.becu.dewi.student656552.articles.presentation.detail_screen

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import nl.becu.dewi.student656552.articles.domain.models.Article
import nl.becu.dewi.student656552.articles.domain.models.ArticleEntity
import nl.becu.dewi.student656552.articles.domain.use_case.ArticleUseCases
import nl.becu.dewi.student656552.articles.presentation.main_screen.MainState
import nl.becu.dewi.student656552.articles.presentation.util.Screen
import javax.inject.Inject

@HiltViewModel
class DetailViewModel @Inject constructor(
    private val articleUseCases: ArticleUseCases
) : ViewModel() {

    private val _state = mutableStateOf(DetailState())
    val state: State<DetailState> = _state

    init {

    }

    fun init(articleId: Int) {
        viewModelScope.launch {
            val article = articleUseCases.getArticle(articleId)
            _state.value = state.value.copy(article)
        }
    }
}