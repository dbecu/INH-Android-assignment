package nl.becu.dewi.student656552.articles.domain.repository

import nl.becu.dewi.student656552.articles.domain.models.RegisterModel
import nl.becu.dewi.student656552.articles.util.Resource

interface UserRepository {

    suspend fun login(userName: String, password: String): Resource<String>

    suspend fun register(userName: String, password: String): Resource<RegisterModel>
}