package nl.becu.dewi.student656552.articles.data.repository

import nl.becu.dewi.student656552.articles.data.data_source.ArticleApi
import nl.becu.dewi.student656552.articles.data.mapper.ArticleMapper
import nl.becu.dewi.student656552.articles.domain.models.Article
import nl.becu.dewi.student656552.articles.domain.models.ArticleResponse
import nl.becu.dewi.student656552.articles.domain.models.exceptions.ArticleException
import nl.becu.dewi.student656552.articles.domain.repository.ArticleRepository
import java.text.SimpleDateFormat
import java.time.LocalDateTime

class ArticleRepositoryImpl(
    private val api: ArticleApi
) : ArticleRepository {

    private val mapper = ArticleMapper()

    override suspend fun getResultArticles(startingIndex: Int, pageSize: Int): Result<List<Article>> {
        val response = api.getArticlesByStartingWith(startingIndex, pageSize)
        val articles = mapper.mapResponseEntityToArticles(response.body())

        when {
            response.isSuccessful -> {
                if (articles.isEmpty()) {
                    throw Exception() //TODO
                } else {
                    return Result.success(articles)
                }
            }
            else -> {
                throw Exception() //TODO
            }

        }
    }

    // TODO: CHANGE THIS LOGIC IS IN THE WRONG LAYER
    override suspend fun getArticleById(id: Int, authToken: String?): Article {
        val articleResponse = api.getArticleById(id, authToken)
        return articleResponse.body()?.let { mapper.mapFirstArticle(it).getOrThrow() } ?: throw Exception() //TODO
    }

    override suspend fun getNextId(startingIndex: Int, pageSize: Int): Int {
        val response = api.getArticlesByStartingWith(startingIndex, pageSize)
        val responseBody = response.body()

        when {
            response.isSuccessful -> {
                if (responseBody?.NextId == null) {
                    throw Exception() //TODO
                } else {
                    return Result.success(responseBody.NextId).getOrThrow()
                }
            } else -> {
                throw Exception() //TODO
            }
        }
    }

    override suspend fun getLikedArticles(authToken: String): List<Article> {
        val response = api.getLikedArticles(authToken)
        val likedArticles = mapper.mapResponseEntityToArticles(response.body()) //TODO .body aint good i think

        when {
            response.isSuccessful -> {
                if (likedArticles == null) {
                    throw Exception() //TODO
                } else {
                    return Result.success(likedArticles).getOrThrow()
                }
            }
            else -> {
                throw Exception() //TODO
            }
        }
    }

    override suspend fun getArticlesByStartingWithAuth(
        startingIndex: Int,
        pageSize: Int,
        authToken: String
    ): Result<List<Article>> {
        val response = api.getArticlesByStartingWithAuth(startingIndex, pageSize, authToken)
        val articles = mapper.mapResponseEntityToArticles(response.body())

        when {
            response.isSuccessful -> {
                if (articles.isEmpty()) {
                    throw Exception() //TODO
                } else {
                    return Result.success(articles)
                }
            }
            else -> {
                throw ArticleException("Auth token expired") //TODO
            }

        }
    }

    override suspend fun putLikeArticle(articleId: Int, authToken: String) {
        api.putLikeArticle(articleId, authToken)    //TODO error handling
    }

    override suspend fun deleteLikeArticle(articleId: Int, authToken: String) {
        api.deleteLikeArticle(articleId, authToken) //TODO error handling
    }

}