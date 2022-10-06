package nl.becu.dewi.student656552.articles.presentation.main_screen

import nl.becu.dewi.student656552.articles.domain.models.Article
import nl.becu.dewi.student656552.articles.util.UiText

data class MainState (
    val articles: List<Article> = emptyList(),
    val page: Int = 134067, //page index
    val load: Int = 20,
    val error: UiText? = null
)