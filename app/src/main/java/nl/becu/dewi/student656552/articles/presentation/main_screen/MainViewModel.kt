package nl.becu.dewi.student656552.articles.presentation.main_screen

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.cachedIn
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import nl.becu.dewi.student656552.articles.domain.use_case.article_use_case.ArticleUseCases
import nl.becu.dewi.student656552.articles.domain.util.DefaultPaginator
import nl.becu.dewi.student656552.articles.presentation.article_pager.ArticlePager
import nl.becu.dewi.student656552.articles.presentation.article_pager.MainPager
import nl.becu.dewi.student656552.articles.presentation.util.SharedPreferencesManager
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    val articleUseCases: ArticleUseCases
) : ViewModel() {

    private val _state = mutableStateOf(MainState())
    val state: State<MainState> = _state

    val articles = Pager(PagingConfig(pageSize = 20)) {
        MainPager(mainViewModel = this)
    }.flow.cachedIn(viewModelScope)


    fun setStartKey(startKey: Int) {
        _state.value = state.value.copy(
            page = startKey
        )
    }

    fun setLoad(load: Int) {
        _state.value = state.value.copy(
            load = load
        )
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