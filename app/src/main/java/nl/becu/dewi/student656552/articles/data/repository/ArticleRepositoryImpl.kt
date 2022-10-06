package nl.becu.dewi.student656552.articles.data.repository

import androidx.compose.ui.res.stringResource
import nl.becu.dewi.student656552.R
import nl.becu.dewi.student656552.articles.data.data_source.ArticleApi
import nl.becu.dewi.student656552.articles.data.mapper.ArticleMapper
import nl.becu.dewi.student656552.articles.domain.models.Article
import nl.becu.dewi.student656552.articles.domain.models.ArticleResponse
import nl.becu.dewi.student656552.articles.domain.models.ArticleResponseEntity
import nl.becu.dewi.student656552.articles.domain.models.exceptions.ArticleException
import nl.becu.dewi.student656552.articles.domain.models.exceptions.ArticleTimeoutException
import nl.becu.dewi.student656552.articles.domain.repository.ArticleRepository
import nl.becu.dewi.student656552.articles.util.Resource
import nl.becu.dewi.student656552.articles.util.UiText
import retrofit2.Response
import java.io.StringReader

class ArticleRepositoryImpl(
    private val api: ArticleApi
) : ArticleRepository {

    private val mapper = ArticleMapper()

    override suspend fun getArticleById(id: Int, authToken: String?): Resource<Article> {
        val response: Response<ArticleResponseEntity>

        try {
            response = api.getArticleById(id, authToken)
        } catch(e: Exception) {
            return Resource.Error(UiText.StringResource(R.string.error_time_out))
        }

        val article = response.body()?.let { mapper.mapFirstArticle(it).getOrNull() }
        return Resource.Success(article)
    }

    override suspend fun getArticles(
        startingIndex: Int,
        pageSize: Int,
        authToken: String?
    ): Result<List<Article>> {

        val response: Response<ArticleResponseEntity>

        try {
            response = if (authToken != null)
                api.getArticles(startingIndex, pageSize, authToken)
            else
                api.getArticles(startingIndex, pageSize)
        } catch(e: Exception) {
            throw ArticleTimeoutException(e.message ?: "")
        }

        val articles = mapper.mapResponseEntityToArticles(response.body())
        articles.sortedByDescending { it.PublishDate }

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
        try {
            api.putLikeArticle(articleId, authToken)
        } catch(e: Exception) {
        throw ArticleTimeoutException(e.message ?: "")
    }

    }

    override suspend fun deleteLikeArticle(articleId: Int, authToken: String) {
        try {
            api.deleteLikeArticle(articleId, authToken) //TODO error handling
        } catch(e: Exception) {
            throw ArticleTimeoutException(e.message ?: "")
        }
    }

    override suspend fun getArticleResponse(
        startingIndex: Int,
        pageSize: Int,
        authToken: String?
    ): Result<ArticleResponse> {
        val response: Response<ArticleResponseEntity>

        try {
            response = if (authToken != null)
                api.getArticles(startingIndex, pageSize, authToken)
            else
                api.getArticles(startingIndex, pageSize)
        } catch(e: Exception) {
            throw ArticleTimeoutException(e.message ?: "")
        }

        val body = response.body() ?: throw Exception()

        return mapper.mapArticleResponse(body)
    }

    override suspend fun getLikedArticleResponse(
        authToken: String
    ): Resource<Result<ArticleResponse>> {
        val response: Response<ArticleResponseEntity>

        try {
            response = api.getLikedArticles(authToken)
        } catch(e: Exception) {
            return Resource.Error(UiText.StringResource(R.string.error_time_out))
        }

        val body = response.body() ?: return Resource.Error(UiText.StringResource(R.string.error))


        return Resource.Success(mapper.mapArticleResponse(body))
    }

}