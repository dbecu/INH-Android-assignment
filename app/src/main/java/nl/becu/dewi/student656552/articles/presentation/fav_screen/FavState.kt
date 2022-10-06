package nl.becu.dewi.student656552.articles.presentation.fav_screen

import nl.becu.dewi.student656552.articles.domain.models.Article
import nl.becu.dewi.student656552.articles.util.UiText

data class FavState (
    val articles: List<Article> = emptyList(),
    val startIndex: Int = 134067,
    val load: Int = 20,
    val error: UiText? = null
)
