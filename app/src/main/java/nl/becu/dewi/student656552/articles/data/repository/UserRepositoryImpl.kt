package nl.becu.dewi.student656552.articles.data.repository

import nl.becu.dewi.student656552.R
import nl.becu.dewi.student656552.articles.data.data_source.UserApi
import nl.becu.dewi.student656552.articles.domain.models.AccountCredentials
import nl.becu.dewi.student656552.articles.domain.models.RegisterModel
import nl.becu.dewi.student656552.articles.domain.repository.UserRepository
import nl.becu.dewi.student656552.articles.util.Resource
import nl.becu.dewi.student656552.articles.util.UiText

class UserRepositoryImpl(
    private val api: UserApi
) : UserRepository {

    override suspend fun login(userName: String, password: String): Resource<String> {
        try {
            val response = api.login(AccountCredentials(userName, password)).body()?.AuthToken
                ?: return Resource.Error(UiText.StringResource(R.string.error_wrong_cred))

            return Resource.Success(response)
        } catch(e: Exception)
        {
            return Resource.Error(UiText.StringResource(R.string.error_time_out))
        }
    }

    override suspend fun register(userName: String, password: String): Resource<RegisterModel> {
        try {
            val response = api.register(AccountCredentials(userName, password)).body()
                ?: return Resource.Error(UiText.StringResource(R.string.error_response))

            if (!response.Success) {
                if (response.Message.contains("UserName already exists"))
                    return Resource.Error(UiText.StringResource(R.string.error_usename_exists))
                else
                    return Resource.Error(UiText.StringResource(R.string.error))
            }

            return Resource.Success(response)
        } catch(e: Exception)
        {
            return Resource.Error(UiText.StringResource(R.string.error_time_out))
        }
    }
}