package nl.becu.dewi.student656552.articles.data.repository

import nl.becu.dewi.student656552.articles.data.data_source.UserApi
import nl.becu.dewi.student656552.articles.domain.models.RegisterModel
import nl.becu.dewi.student656552.articles.domain.repository.UserRepository

class UserRepositoryImpl(
    private val api: UserApi
) : UserRepository {

    override suspend fun login(userName: String, password: String): String {
        return api.login(userName, password).body() ?: throw Exception() //TODO
    }

    override suspend fun register(userName: String, password: String): RegisterModel {
        TODO("Not yet implemented")
    }
}