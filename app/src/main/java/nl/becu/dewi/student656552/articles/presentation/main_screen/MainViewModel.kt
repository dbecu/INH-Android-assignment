package nl.becu.dewi.student656552.articles.presentation.main_screen

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

@HiltViewModel
class MainViewModel @Inject constructor(
    val articleUseCases: ArticleUseCases
) : ViewModel() {

    private val _state = mutableStateOf(MainState())
    val state: State<MainState> = _state

    val articles = Pager(PagingConfig(pageSize = 20)) {
        ArticlePager(articleUseCases, false)
    }.flow.cachedIn(viewModelScope)

}