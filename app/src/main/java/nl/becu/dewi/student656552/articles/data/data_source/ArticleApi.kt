package nl.becu.dewi.student656552.articles.data.data_source

import nl.becu.dewi.student656552.articles.domain.models.ArticleResponseEntity
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.*

interface ArticleApi {

    @Headers("Content-Type: application/json")
    @GET("{articleID}")
    suspend fun getArticleById(
        @Path("articleID") id: Int,
        @Header("x-authtoken") token: String?,
        ): Response<ArticleResponseEntity>

    @Headers("Content-Type: application/json")
    @GET("{articleID}")
    suspend fun getArticles(
        @Path("articleID") id: Int,
        @Query("count") amount: Int,
        @Header("x-authtoken") token: String
    ): Response<ArticleResponseEntity>

    @Headers("Content-Type: application/json")
    @GET("{articleID}")
    suspend fun getArticles(
        @Path("articleID") id: Int,
        @Query("count") amount: Int
    ): Response<ArticleResponseEntity>

    @Headers("Content-Type: application/json")
    @GET("liked")
    suspend fun getLikedArticles(
        @Header("x-authtoken") token: String
    ): Response<ArticleResponseEntity>

    @Headers("Content-Type: application/json")
    @PUT("{articleID}/liked")
    suspend fun putLikeArticle(
        @Path("articleID") id: Int,
        @Header("x-authtoken") token: String,
    ): Response<Unit>

    @Headers("Content-Type: application/json")
    @DELETE("{articleID}/like")
    suspend fun deleteLikeArticle(
        @Path("articleID") id: Int,
        @Header("x-authtoken") token: String,
    ): Response<Unit>
}