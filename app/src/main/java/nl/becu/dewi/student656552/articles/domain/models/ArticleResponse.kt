package nl.becu.dewi.student656552.articles.domain.models

data class ArticleResponse(
    val Results: List<Article>,
    val NextId: Int
)