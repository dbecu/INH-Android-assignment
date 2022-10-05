package nl.becu.dewi.student656552.articles.presentation.fav_screen

import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.paging.PagingSource
import androidx.paging.PagingState
import nl.becu.dewi.student656552.articles.domain.models.Article
import nl.becu.dewi.student656552.articles.domain.use_case.ArticleUseCases
import nl.becu.dewi.student656552.articles.presentation.util.SharedPreferencesManager

class ArticlePager constructor(val viewModel: FavViewModel)
    : PagingSource<Int, Article>() {

    override fun getRefreshKey(state: PagingState<Int, Article>): Int? {
        return null
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Article> {
        val result = fetch(params.key ?: 134067, params.loadSize)
            .getOrElse {
                return LoadResult.Error(it)
            }
        return LoadResult.Page(result, null, (params.key ?: 134067) + 1) //TODO: next page //call api to get to the nexr key
    }

    override val keyReuseSupported: Boolean = true

    private suspend fun fetch(startKey: Int, loadSize: Int): Result<List<Article>> {
        return viewModel.articleUseCases.getResultArticles(startKey, loadSize)
    }
}