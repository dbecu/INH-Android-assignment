package nl.becu.dewi.student656552.articles.presentation.main_screen

import nl.becu.dewi.student656552.articles.domain.models.Article

data class MainState (
    val articles: List<Article> = emptyList(),
    //val articlesOrder: ArticleOrder = ArticleOrder.Title(OrderType.Descending),
    val isOrderSectionVisible: Boolean = false,

    //pagination
    val isLoading: Boolean = false,
    val error: String? = null,
    val endReached: Boolean = false,
    val page: Int = 134067, //page index

    val load: Int = 20
)