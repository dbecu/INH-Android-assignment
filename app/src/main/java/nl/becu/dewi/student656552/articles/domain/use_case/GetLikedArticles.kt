package nl.becu.dewi.student656552.articles.domain.use_case

import nl.becu.dewi.student656552.articles.domain.models.Article
import nl.becu.dewi.student656552.articles.domain.repository.ArticleRepository

class GetLikedArticles (
    private val repository: ArticleRepository
) {
    suspend operator fun invoke(authToken: String): List<Article> {
        return repository.getLikedArticles(authToken)
    }
}