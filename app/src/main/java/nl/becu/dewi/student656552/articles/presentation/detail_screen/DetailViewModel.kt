package nl.becu.dewi.student656552.articles.presentation.detail_screen

import android.graphics.drawable.Icon
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import nl.becu.dewi.student656552.articles.domain.use_case.ArticleUseCases
import nl.becu.dewi.student656552.articles.presentation.util.SharedPreferencesManager
import javax.inject.Inject

@HiltViewModel
class DetailViewModel @Inject constructor(
    private val articleUseCases: ArticleUseCases
) : ViewModel() {

    private val _state = mutableStateOf(DetailState(
        authToken = "",
        isFavedIcon = false
    ))
    val state: State<DetailState> = _state

    fun onEvent(event: DetailEvent) {
        when (event) {
            is DetailEvent.PutArticleLike -> {
                viewModelScope.launch {
                    state.value.authToken?.let {
                        articleUseCases.putLikeArticle(
                            state.value.article?.Id ?: 0,
                            it
                        )
                    }
                }
            }
            is DetailEvent.DeleteArticleLike -> {
                viewModelScope.launch {
                    state.value.authToken?.let {
                        articleUseCases.deleteLikeArticle(
                            state.value.article?.Id ?: 0,
                            it
                        )
                    }
                }
            }
            is DetailEvent.GetAuthToken -> {
                viewModelScope.launch {
                    _state.value = state.value.copy(
                        authToken = event.authToken
                    )
                }
            }
        }
    }

    fun init(articleId: Int) {
        viewModelScope.launch {
            val article = articleUseCases.getArticle(articleId, SharedPreferencesManager.getAuthToken())
            _state.value = state.value.copy(article)
        }
    }
}