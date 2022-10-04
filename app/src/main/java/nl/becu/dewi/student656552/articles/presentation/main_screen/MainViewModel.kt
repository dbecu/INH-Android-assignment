package nl.becu.dewi.student656552.articles.presentation.main_screen

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import nl.becu.dewi.student656552.articles.domain.use_case.ArticleUseCases
import nl.becu.dewi.student656552.articles.domain.util.DefaultPaginator
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val articleUseCases: ArticleUseCases
) : ViewModel() {

    private val _state = mutableStateOf(MainState())
    val state: State<MainState> = _state

    private val paginator = DefaultPaginator(
        initialKey = _state.value.page,
        onLoadUpdated = {
            _state.value = _state.value.copy(isLoading = it)
        },
        onRequest = { nextPage ->
            articleUseCases.getResultArticles(nextPage, 20)
        },
        getNextKey = { nextPage ->
            articleUseCases.getNextId(nextPage, 20)
        },
        onError = {
            _state.value = _state.value.copy(error = it?.localizedMessage)
        },
        onSuccess = { items, newKey ->
            _state.value = _state.value.copy(
                articles = _state.value.articles + items,
                page = newKey,
                endReached = items.isEmpty()
            )
        }
    )

    init{
        loadNextItems()
    }

    fun loadNextItems() {
        viewModelScope.launch {
            paginator.loadNextItems()
        }
    }

    fun reset(){
        paginator.reset()
    }


    /*
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

     */
}