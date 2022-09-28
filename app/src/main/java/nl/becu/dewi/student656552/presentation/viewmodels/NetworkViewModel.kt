package nl.becu.dewi.student656552.presentation.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.cachedIn
import dagger.hilt.android.lifecycle.HiltViewModel
import nl.becu.dewi.student656552.domain.use_case.ArticleUseCases
import javax.inject.Inject

@HiltViewModel
class NetworkViewModel @Inject constructor(
    private val articleUseCases: ArticleUseCases
) : ViewModel() {

    val articles = Pager(PagingConfig(pageSize = 10)){
        ArticlePager(articleUseCases)
    }.flow.cachedIn(viewModelScope)
}