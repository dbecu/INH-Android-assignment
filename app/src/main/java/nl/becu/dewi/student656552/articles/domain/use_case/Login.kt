package nl.becu.dewi.student656552.articles.domain.use_case

import nl.becu.dewi.student656552.articles.domain.models.Article
import nl.becu.dewi.student656552.articles.domain.repository.ArticleRepository
import nl.becu.dewi.student656552.articles.domain.repository.UserRepository

class Login(
    private val repository: UserRepository
){
    suspend operator fun invoke(userName: String, password: String): String {
        return repository.login(userName, password)
    }
}