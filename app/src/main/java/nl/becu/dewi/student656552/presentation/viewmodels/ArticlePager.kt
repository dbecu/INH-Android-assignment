package nl.becu.dewi.student656552.presentation.viewmodels

import androidx.paging.PagingSource
import androidx.paging.PagingState
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import nl.becu.dewi.student656552.data.data_source.ArticleApi
import nl.becu.dewi.student656552.data.mapper.ArticleMapper
import nl.becu.dewi.student656552.domain.models.Article
import nl.becu.dewi.student656552.domain.use_case.ArticleUseCases
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.create

class ArticlePager(
    private val articleUseCases: ArticleUseCases
) : PagingSource<Int, Article>() {

    override val keyReuseSupported: Boolean = true

    private suspend fun fetch(startKey: Int = 1, loadSize: Int = 2): Result<List<Article>>{
        return articleUseCases.getArticles()
    }

    init{
        GlobalScope.launch{
            fetch(1, 2)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, Article>): Int? {
        return null
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Article> {
        val result = fetch(params.key ?: -1, params.loadSize)
            .getOrElse{
                return LoadResult.Error(it)
            }

        return LoadResult.Page(result,null,(params.key ?: 0) + 1)
    }

}