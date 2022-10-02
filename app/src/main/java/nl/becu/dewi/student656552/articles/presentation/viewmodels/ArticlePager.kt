package nl.becu.dewi.student656552.articles.presentation.viewmodels

import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import nl.becu.dewi.student656552.articles.data.data_source.ArticleApi
import nl.becu.dewi.student656552.articles.data.mapper.ArticleMapper
import nl.becu.dewi.student656552.articles.domain.models.Article
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.create

class ArticlePager /*: PagingSource<Int, Article>() */ {
    private val articleMapper: ArticleMapper = ArticleMapper()

    private val retrofit = Retrofit.Builder()
        .baseUrl("https://inhollandbackend.azurewebsites.net/api/")
        .addConverterFactory(MoshiConverterFactory.create())
        .build()

    private val api: ArticleApi = retrofit.create<ArticleApi>()

    //override val keyReuseSupported: Boolean = true

    private suspend fun fetch(startKey: Int, loadSize: Int): Result<List<Article>>{
        val response = api.getArticles(loadSize.coerceIn(1, 10))
        return when {
            response.isSuccessful -> {
                val body = response.body()
                if (body != null){
                    articleMapper.mapArticles(body)
                } else{
                    Result.failure(java.lang.IllegalStateException("Body was empty"))
                }
            }
            else -> Result.failure(java.lang.IllegalStateException("Something went wrong"))
        }
    }


    init{
        GlobalScope.launch{
            fetch(1, 2)
        }
    }

    /*
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

     */

}