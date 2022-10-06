package nl.becu.dewi.student656552.articles.domain.use_case.user_use_case

import nl.becu.dewi.student656552.articles.domain.models.Article
import nl.becu.dewi.student656552.articles.domain.repository.ArticleRepository
import nl.becu.dewi.student656552.articles.domain.repository.UserRepository
import nl.becu.dewi.student656552.articles.util.Resource

class Login(
    private val repository: UserRepository
){
    suspend operator fun invoke(userName: String, password: String): Resource<String> {
        return repository.login(userName, password)
    }
}