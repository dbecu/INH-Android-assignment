package nl.becu.dewi.student656552.articles.presentation.fav_screen

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.cachedIn
import dagger.hilt.android.lifecycle.HiltViewModel
import nl.becu.dewi.student656552.articles.domain.use_case.article_use_case.ArticleUseCases
import nl.becu.dewi.student656552.articles.presentation.article_pager.ArticlePager
import javax.inject.Inject
//import androidx.paging.compose.collectAsLazyPagingItems


@HiltViewModel
class FavViewModel @Inject constructor(
    val articleUseCases: ArticleUseCases
) : ViewModel() {

    private val _state = mutableStateOf(FavState())
    val state: State<FavState> = _state

    val articles = Pager(PagingConfig(pageSize = 20)) {
        ArticlePager(favViewModel = this)
    }.flow.cachedIn(viewModelScope)

    fun setStartKey(startKey: Int) {
        _state.value = state.value.copy(
            startIndex = startKey
        )
    }

    fun setLoad(load: Int) {
        _state.value = state.value.copy(
            load = load
        )
    }
}