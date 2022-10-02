package nl.becu.dewi.student656552.articles.domain.repository

import nl.becu.dewi.student656552.articles.domain.models.Article

interface ArticleRepository {

    suspend fun getArticles(): List<Article>

    suspend fun getArticleById(id: Int): Article
}