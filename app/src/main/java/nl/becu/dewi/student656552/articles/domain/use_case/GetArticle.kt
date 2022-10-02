package nl.becu.dewi.student656552.articles.domain.use_case

import nl.becu.dewi.student656552.articles.domain.models.Article
import nl.becu.dewi.student656552.articles.domain.repository.ArticleRepository

class GetArticle(
    private val repository: ArticleRepository
) {
    /*suspend operator fun invoke(id: Int): Result<Article> {
        return repository.getArticleById(id)
    }

     */


    /*
    fun something(){
        val response = api.getArticleById(id)

        val result =  when {
            response.isSuccessful -> {
                val body = response.body()
                if (body != null){
                    articleMapper.mapFirstArticle(body)
                } else{
                    Result.failure(java.lang.IllegalStateException("Body was empty"))
                }
            }
            else -> Result.failure(java.lang.IllegalStateException("Something went wrong"))
        }

        return result.getOrNull()
    }

     */
}