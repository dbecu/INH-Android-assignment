package nl.becu.dewi.student656552.articles.domain.use_case

import nl.becu.dewi.student656552.articles.domain.models.Article
import nl.becu.dewi.student656552.articles.domain.repository.ArticleRepository

class GetResultArticles (
    private val repository: ArticleRepository
) {
    suspend operator fun invoke(nextPage: Int, amount: Int): Result<List<Article>> {
        return repository.getResultArticles(nextPage, amount)
    }
}