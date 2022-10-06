package nl.becu.dewi.student656552.articles.domain.use_case.article_use_case

import nl.becu.dewi.student656552.R
import nl.becu.dewi.student656552.articles.domain.models.Article
import nl.becu.dewi.student656552.articles.domain.models.ArticleResponse
import nl.becu.dewi.student656552.articles.domain.repository.ArticleRepository
import nl.becu.dewi.student656552.articles.util.Resource
import nl.becu.dewi.student656552.articles.util.UiText

class GetArticleResponse(
    private val repository: ArticleRepository
) {

    suspend operator fun invoke(
        startingIndex: Int,
        pageSize: Int,
        authToken: String?
    ): Resource<Pair<Int, Result<List<Article>>>> {

        val articleResponse = repository.getArticleResponse(startingIndex, pageSize, authToken).data

        if (articleResponse?.isFailure ?: true)
            return Resource.Error(UiText.StringResource(R.string.error))

        val nextId = articleResponse?.getOrNull()?.NextId ?: return Resource.Error(UiText.StringResource(R.string.error))
        val articles = articleResponse?.getOrNull()?.Results ?: return Resource.Error(UiText.StringResource(R.string.error))

        return Resource.Success(Pair(nextId, Result.success(articles)))
    }
}