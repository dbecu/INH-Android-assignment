package nl.becu.dewi.student656552.articles.domain.use_case.article_use_case

import nl.becu.dewi.student656552.articles.domain.repository.ArticleRepository
import nl.becu.dewi.student656552.articles.util.Resource

class DeleteLikeArticle (
    private val repository: ArticleRepository
) {
    suspend operator fun invoke(articleId: Int, authToken: String) : Resource<Unit> {
        return repository.deleteLikeArticle(articleId, authToken)
    }
}