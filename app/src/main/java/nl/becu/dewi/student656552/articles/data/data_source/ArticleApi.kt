package nl.becu.dewi.student656552.articles.data.data_source

import nl.becu.dewi.student656552.articles.domain.models.ArticleResponseEntity
import nl.becu.dewi.student656552.articles.domain.models.RegisterModelEntity
import retrofit2.Callback
import retrofit2.Response
import retrofit2.http.*

interface ArticleApi {

    @GET("Articles")
    suspend fun getArticles(
        @Query("count") amount: Int
    ): Response<ArticleResponseEntity>

    @GET("Articles/{articleID}")
    suspend fun getArticleById(
        @Path("articleID") id: Int
    ): Response<ArticleResponseEntity>

    @FormUrlEncoded
    @POST("User/login")
    suspend fun login(
        @Field("UserName") userName: String,
        @Field("Password") password: String
    ) : Response<String>

    @FormUrlEncoded
    @POST("User/register")
    suspend fun register(
        @Field("UserName") userName: String,
        @Field("Password") password: String
    ) : Response<RegisterModelEntity>
}