package nl.becu.dewi.student656552.data.data_source

import nl.becu.dewi.student656552.domain.models.ArticleResponseEntity
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ArticleApi {
    @GET("Articles")
    suspend fun getArticles(
        @Query("count") amount: Int
    ): Response<ArticleResponseEntity>

    @GET("Articles/{articleID}")
    suspend fun getArticleById(
        @Path("articleID") amount: Int
    ): Response<ArticleResponseEntity>

}