package nl.becu.dewi.student656552.articles.presentation.detail_screen

import nl.becu.dewi.student656552.articles.domain.models.Article

data class DetailState(
    val article: Article? = null, //TODO should never be null
    val authToken: String?
)