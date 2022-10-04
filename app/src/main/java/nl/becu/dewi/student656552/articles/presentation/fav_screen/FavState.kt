package nl.becu.dewi.student656552.articles.presentation.fav_screen

import nl.becu.dewi.student656552.articles.domain.models.Article

data class FavState (
    val articles: List<Article> = emptyList(),
    val authToken: String

)
