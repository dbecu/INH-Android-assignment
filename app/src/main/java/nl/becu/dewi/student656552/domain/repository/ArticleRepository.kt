package nl.becu.dewi.student656552.domain.repository

import nl.becu.dewi.student656552.domain.models.Article
import nl.becu.dewi.student656552.domain.models.ArticleResponse

interface ArticleRepository {

    suspend fun getArticles(): List<Article>

    suspend fun getArticleById(id: Int): Article?
}