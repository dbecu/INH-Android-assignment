package nl.becu.dewi.student656552.articles.domain.repository

import nl.becu.dewi.student656552.articles.domain.models.RegisterModel

interface UserRepository {

    suspend fun login(userName: String, password: String): String

    suspend fun register(userName: String, password: String): RegisterModel
}