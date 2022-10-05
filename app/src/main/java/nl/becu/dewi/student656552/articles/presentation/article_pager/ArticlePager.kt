package nl.becu.dewi.student656552.articles.presentation.article_pager

import androidx.paging.PagingSource
import androidx.paging.PagingState
import nl.becu.dewi.student656552.articles.domain.models.Article
import nl.becu.dewi.student656552.articles.presentation.fav_screen.FavViewModel
import nl.becu.dewi.student656552.articles.presentation.main_screen.MainViewModel
import nl.becu.dewi.student656552.articles.presentation.util.SharedPreferencesManager

class ArticlePager constructor(val favViewModel: FavViewModel)
    : PagingSource<Int, Article>() {

    override fun getRefreshKey(state: PagingState<Int, Article>): Int? {
        return null
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Article> {
        val result = fetch(params.key ?: 134067, params.loadSize)
            .getOrElse {
                return LoadResult.Error(it)
            }

        return LoadResult.Page(result, null, favViewModel.articleUseCases.getNextId(favViewModel.state.value.startIndex, favViewModel.state.value.load)) //TODO: next page //call api to get to the nexr key
    }

    override val keyReuseSupported: Boolean = true

    private suspend fun fetch(startKey: Int, loadSize: Int): Result<List<Article>> {
        favViewModel.setStartKey(startKey)
        favViewModel.setLoad(loadSize)

        return favViewModel.articleUseCases.getArticles(startKey, loadSize, SharedPreferencesManager.getAuthToken())
    }
}