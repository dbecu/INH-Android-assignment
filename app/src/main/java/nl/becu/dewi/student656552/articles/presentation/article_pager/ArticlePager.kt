package nl.becu.dewi.student656552.articles.presentation.article_pager

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.paging.PagingSource
import androidx.paging.PagingState
import nl.becu.dewi.student656552.R
import nl.becu.dewi.student656552.articles.domain.models.Article
import nl.becu.dewi.student656552.articles.domain.use_case.article_use_case.ArticleUseCases
import nl.becu.dewi.student656552.articles.presentation.detail_screen.DetailState
import nl.becu.dewi.student656552.articles.presentation.fav_screen.FavViewModel
import nl.becu.dewi.student656552.articles.presentation.main_screen.MainViewModel
import nl.becu.dewi.student656552.articles.presentation.util.SharedPreferencesManager
import nl.becu.dewi.student656552.articles.util.Resource
import nl.becu.dewi.student656552.articles.util.UiText

class ArticlePager constructor(
    val articleUseCases: ArticleUseCases,
    val isFav: Boolean = false)
: PagingSource<Int, Article>() {

    private val _error = mutableStateOf(ArticlePagerState())
    val error: State<ArticlePagerState> = _error

    var errorMessage: UiText? = null

    override fun getRefreshKey(state: PagingState<Int, Article>): Int? {
        return null
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Article> {
        val key = params.key ?: 134067
        val loadSize = params.loadSize

        val articleResponse = if (isFav) {
            articleUseCases.getLikedArticleResponse(
                SharedPreferencesManager.getAuthToken()
            )
        } else {
            articleUseCases.getArticleResponse(
                key,
                loadSize,
                SharedPreferencesManager.getAuthToken()
            )
        }

        when (articleResponse) {
            is Resource.Success -> {
                val nextKey = articleResponse.data?.first
                val articles = articleResponse.data?.second!!

                val result = articles.getOrElse {
                    return LoadResult.Error(it)
                }

                _error.value = error.value.copy(
                    error = null
                )

                errorMessage = null

                return LoadResult.Page(
                    result,
                    null,
                    if (nextKey == 0) {
                        null
                    } else {
                        nextKey
                    }
                )
            }
            is Resource.Error -> {
                _error.value = error.value.copy(
                    error = articleResponse.message
                )

                errorMessage = articleResponse.message
                return LoadResult.Error(Exception())
            }
        }
    }

    override val keyReuseSupported: Boolean = true

}