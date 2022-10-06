package nl.becu.dewi.student656552.articles.domain.use_case.article_use_case

import nl.becu.dewi.student656552.articles.domain.models.Article
import nl.becu.dewi.student656552.articles.domain.repository.ArticleRepository
import nl.becu.dewi.student656552.articles.util.Resource

class GetArticle(
    private val repository: ArticleRepository
) {

    suspend operator fun invoke(id: Int?, authToken: String?): Resource<Article> {
        if (id == null)
        {
            return Resource.Error("Article id was not provided, please try again later")
        }

        val article =  repository.getArticleById(id, authToken)

        if (article.data == null) {
            return Resource.Error("Could not get the article, please try again later")
        }

        return article
    }




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