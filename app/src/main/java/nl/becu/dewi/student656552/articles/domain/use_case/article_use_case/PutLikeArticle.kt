package nl.becu.dewi.student656552.articles.domain.use_case.article_use_case

import nl.becu.dewi.student656552.articles.domain.models.Article
import nl.becu.dewi.student656552.articles.domain.repository.ArticleRepository
import nl.becu.dewi.student656552.articles.util.Resource

class PutLikeArticle (
    private val repository: ArticleRepository
) {
    suspend operator fun invoke(articleId: Int, authToken: String) : Resource<Unit> {
        return repository.putLikeArticle(articleId, authToken)
    }
}