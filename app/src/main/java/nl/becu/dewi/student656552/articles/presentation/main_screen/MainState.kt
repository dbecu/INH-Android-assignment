package nl.becu.dewi.student656552.articles.presentation.main_screen

import androidx.paging.PagingSource
import nl.becu.dewi.student656552.articles.domain.models.Article
import nl.becu.dewi.student656552.articles.presentation.article_pager.ArticlePager
import nl.becu.dewi.student656552.articles.util.UiText

data class MainState (
    val articles: List<Article> = emptyList(),

    val page: Int = 134067, //page index
    val load: Int = 20,
    val mainPager: PagingSource<Int, Article>? = null,

    val error: UiText? = null
)