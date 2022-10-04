package nl.becu.dewi.student656552.articles.data.data_source

import nl.becu.dewi.student656552.articles.domain.models.AccountCredentials
import nl.becu.dewi.student656552.articles.domain.models.LoginResponse
import nl.becu.dewi.student656552.articles.domain.models.RegisterModel
import retrofit2.Response
import retrofit2.http.*

interface UserApi {

    @Headers("Content-Type: application/json")
    //@FormUrlEncoded
    @POST("login")
    suspend fun login(
        @Body body: AccountCredentials
    ) : Response<LoginResponse>

    @FormUrlEncoded
    @POST("register")
    suspend fun register(
        @Field("UserName") userName: String,
        @Field("Password") password: String
    ) : Response<RegisterModel>
}