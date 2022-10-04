package nl.becu.dewi.student656552.articles.domain.repository

import nl.becu.dewi.student656552.articles.domain.models.Article

interface ArticleRepository {
    suspend fun getArticleById(id: Int): Article

    suspend fun getResultArticles(startingIndex: Int, pageSize: Int): Result<List<Article>>

    suspend fun getNextId(startingIndex: Int, pageSize: Int): Int

    suspend fun getLikedArticles(authToken: String): List<Article>

    suspend fun getArticlesByStartingWithAuth(startingIndex: Int, pageSize: Int, authToken: String): Result<List<Article>>


}