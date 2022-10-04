package nl.becu.dewi.student656552.articles.domain.use_case

import nl.becu.dewi.student656552.articles.domain.models.Article
import nl.becu.dewi.student656552.articles.domain.repository.ArticleRepository

class PutLikeArticle (
    private val repository: ArticleRepository
) {
    suspend operator fun invoke(articleId: Int, authToken: String)  {
        return repository.putLikeArticle(articleId, authToken)
    }
}