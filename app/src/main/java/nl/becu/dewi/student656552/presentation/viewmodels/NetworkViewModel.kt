package nl.becu.dewi.student656552.presentation.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.cachedIn

class NetworkViewModel : ViewModel() {

    val articles = Pager(PagingConfig(pageSize = 10)){
        ArticlePager()
    }.flow.cachedIn(viewModelScope)
}