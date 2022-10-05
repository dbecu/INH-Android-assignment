package nl.becu.dewi.student656552.articles.domain.repository

import nl.becu.dewi.student656552.articles.domain.models.Article

interface ArticleRepository {
    suspend fun getArticleById(id: Int, authToken: String?): Article

    suspend fun getNextId(startingIndex: Int, pageSize: Int): Int

    suspend fun getArticles(startingIndex: Int, pageSize: Int, authToken: String?): Result<List<Article>>

    suspend fun putLikeArticle(articleId: Int, authToken: String): Unit

    suspend fun deleteLikeArticle(articleId: Int, authToken: String): Unit

}