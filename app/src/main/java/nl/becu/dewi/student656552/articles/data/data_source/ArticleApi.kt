package nl.becu.dewi.student656552.articles.data.data_source

import nl.becu.dewi.student656552.articles.domain.models.ArticleResponseEntity
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.*

interface ArticleApi {

    @GET("")
    suspend fun getArticles(
        @Query("count") amount: Int
    ): Response<ArticleResponseEntity>

    @GET("{articleID}")
    suspend fun getArticleById(
        @Path("articleID") id: Int
    ): Response<ArticleResponseEntity>

    @GET("{articleID}")
    suspend fun getArticlesByStartingWith(
        @Path("articleID") id: Int,
        @Query("count") amount: Int
    ): Response<ArticleResponseEntity>


    @GET("Articles/liked")
    suspend fun getLikedArticles(
        @Field("x-authtoken") token: String,
    ): Response<ArticleResponseEntity>


}