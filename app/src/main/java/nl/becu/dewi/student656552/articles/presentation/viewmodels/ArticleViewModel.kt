package nl.becu.dewi.student656552.articles.presentation.viewmodels

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import nl.becu.dewi.student656552.articles.domain.use_case.ArticleUseCases

class ArticleViewModel(
    private val articleUseCases: ArticleUseCases
) : ViewModel() {

    /*
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

     */


    init{
        GlobalScope.launch{
            //fetch()
            articleUseCases.getArticle(1)
        }
    }
}