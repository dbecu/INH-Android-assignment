package nl.becu.dewi.student656552.articles.domain.use_case.user_use_case

import nl.becu.dewi.student656552.articles.domain.models.RegisterModel
import nl.becu.dewi.student656552.articles.domain.repository.UserRepository

class Register(
    private val repository: UserRepository
){
    suspend operator fun invoke(userName: String, password: String): RegisterModel {
        return repository.register(userName, password)
    }
}