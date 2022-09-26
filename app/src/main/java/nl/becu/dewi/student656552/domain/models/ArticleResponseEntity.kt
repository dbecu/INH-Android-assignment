package nl.becu.dewi.student656552.domain.models

import nl.becu.dewi.student656552.domain.models.Article

data class ArticleResponseEntity (
    val Results: List<Article>?,
    val NextId: Int?
)
