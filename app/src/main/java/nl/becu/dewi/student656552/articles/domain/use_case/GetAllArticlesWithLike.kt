package nl.becu.dewi.student656552.articles.domain.use_case

import nl.becu.dewi.student656552.articles.domain.models.Article
import nl.becu.dewi.student656552.articles.domain.repository.ArticleRepository

class GetAllArticlesWithLike(
    private val repository: ArticleRepository
) {

    suspend operator fun invoke(startingIndex: Int, pageSize: Int, authToken: String): Result<List<Article>> {
        return repository.getArticlesByStartingWithAuth(startingIndex, pageSize, authToken)
    }
}