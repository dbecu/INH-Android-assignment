package nl.becu.dewi.student656552.articles.domain.use_case.article_use_case

import nl.becu.dewi.student656552.R
import nl.becu.dewi.student656552.articles.domain.models.Article
import nl.becu.dewi.student656552.articles.domain.repository.ArticleRepository
import nl.becu.dewi.student656552.articles.util.Resource
import nl.becu.dewi.student656552.articles.util.UiText

class GetArticle(
    private val repository: ArticleRepository
) {

    suspend operator fun invoke(id: Int?, authToken: String?): Resource<Article> {

        if (id == null) {
            return Resource.Error(UiText.StringResource(R.string.error_no_id_provided))
        }

        return repository.getArticleById(id, authToken)
    }
}