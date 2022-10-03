package nl.becu.dewi.student656552.articles.domain.repository

import nl.becu.dewi.student656552.articles.domain.models.Article

interface ArticleRepository {
    suspend fun getArticles(): List<Article>

    suspend fun getArticleById(id: Int): Article

    suspend fun getResultArticles(startingIndex: Int, pageSize: Int): Result<List<Article>>

    suspend fun getNextId(startingIndex: Int, pageSize: Int): Int

}