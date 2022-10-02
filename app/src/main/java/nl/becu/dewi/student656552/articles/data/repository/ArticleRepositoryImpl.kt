package nl.becu.dewi.student656552.articles.data.repository

import nl.becu.dewi.student656552.articles.data.data_source.ArticleApi
import nl.becu.dewi.student656552.articles.domain.models.Article
import nl.becu.dewi.student656552.articles.domain.models.ArticleResponse
import nl.becu.dewi.student656552.articles.domain.repository.ArticleRepository

class ArticleRepositoryImpl(
    private val api: ArticleApi
) : ArticleRepository {

    override suspend fun getArticles(): List<Article> {
        //startKey: Int, loadSize: Int

        val articleResponse = api.getArticles(10)

        return articleResponse.body()?.Results?.map {
            with(it) {
                Article(
                    Id = Id!!,
                    Feed = Feed!!,
                    Title = Title!!,
                    Summary = Summary!!,
                    Image = Image!!
                )
            }
        } ?: emptyList()

        /*
        val response = api.getArticles(2)
        val articles = when {
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

        return articles.getOrDefault(emptyList())

         */
    }

    override suspend fun getArticleById(id: Int): Article?{
        /*
        val response = api.getArticleById(id)

        val result = when {
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

        //Can change so it's just result --> then it returns Result<Article>
        return result.getOrNull()

         */

        return null
    }


    private suspend fun getArticleResponseById(id: Int): ArticleResponse? {
        /*
        val response = api.getArticleById(id)

        val result = when {
            response.isSuccessful -> {
                val body = response.body()
                if (body != null){
                    articleMapper.mapArticleResponse(body)
                } else{
                    Result.failure(java.lang.IllegalStateException("Body was empty"))
                }
            }
            else -> Result.failure(java.lang.IllegalStateException("Something went wrong"))
        }

        return result.getOrNull()

         */

        return ArticleResponse(emptyList(), 1)
    }

}