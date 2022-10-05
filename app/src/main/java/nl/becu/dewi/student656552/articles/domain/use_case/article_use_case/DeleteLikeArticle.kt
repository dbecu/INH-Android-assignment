package nl.becu.dewi.student656552.articles.domain.use_case.article_use_case

import nl.becu.dewi.student656552.articles.domain.repository.ArticleRepository

class DeleteLikeArticle (
    private val repository: ArticleRepository
) {
    suspend operator fun invoke(articleId: Int, authToken: String)  {
        return repository.deleteLikeArticle(articleId, authToken)
    }
}