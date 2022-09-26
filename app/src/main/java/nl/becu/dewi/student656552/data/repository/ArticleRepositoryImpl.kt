package nl.becu.dewi.student656552.data.repository

import nl.becu.dewi.student656552.data.data_source.ArticleApi
import nl.becu.dewi.student656552.domain.models.Article
import nl.becu.dewi.student656552.domain.models.ArticleResponse
import nl.becu.dewi.student656552.domain.repository.ArticleRepository

class ArticleRepositoryImpl(
) : BaseRepository(), ArticleRepository{

    override suspend fun getArticles(): Result<List<Article>> {
        //startKey: Int, loadSize: Int
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

        return articles
    }

    override suspend fun getArticleById(id: Int): Result<Article> {
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
        return result
    }


    private suspend fun getArticleResponseById(id: Int): ArticleResponse? {
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
    }

}