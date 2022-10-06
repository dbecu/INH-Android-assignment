package nl.becu.dewi.student656552.articles.presentation.article_pager

import androidx.paging.PagingSource
import androidx.paging.PagingState
import nl.becu.dewi.student656552.articles.domain.models.Article
import nl.becu.dewi.student656552.articles.domain.use_case.article_use_case.ArticleUseCases
import nl.becu.dewi.student656552.articles.presentation.fav_screen.FavViewModel
import nl.becu.dewi.student656552.articles.presentation.main_screen.MainViewModel
import nl.becu.dewi.student656552.articles.presentation.util.SharedPreferencesManager

class ArticlePager constructor(
    val articleUseCases: ArticleUseCases,
    val isFav: Boolean = false)
: PagingSource<Int, Article>() {

    override fun getRefreshKey(state: PagingState<Int, Article>): Int? {
        return null
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Article> {
        val key = params.key ?: 134067
        val loadSize = params.loadSize

        val articleResponse = if (isFav) {
            articleUseCases.getLikedArticleResponse(
                SharedPreferencesManager.getAuthToken())
        } else {
            articleUseCases.getArticleResponse(
                key,
                loadSize,
                SharedPreferencesManager.getAuthToken())
        }

        //get data
        val nextKey = articleResponse.data?.first
        val articles = articleResponse.data?.second ?: throw Exception()


        val result = articles.getOrElse {
                return LoadResult.Error(it) }

        return LoadResult.Page(result, null,
            if (nextKey == 0) {
                null
            } else {
                nextKey })
    }

    override val keyReuseSupported: Boolean = true

}