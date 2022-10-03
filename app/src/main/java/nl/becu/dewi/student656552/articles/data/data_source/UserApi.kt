package nl.becu.dewi.student656552.articles.data.data_source

import nl.becu.dewi.student656552.articles.domain.models.RegisterModel
import retrofit2.Response
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

interface UserApi {
    @FormUrlEncoded
    @POST("login")
    suspend fun login(
        @Field("UserName") userName: String,
        @Field("Password") password: String
    ) : Response<String>

    @FormUrlEncoded
    @POST("register")
    suspend fun register(
        @Field("UserName") userName: String,
        @Field("Password") password: String
    ) : Response<RegisterModel>
}