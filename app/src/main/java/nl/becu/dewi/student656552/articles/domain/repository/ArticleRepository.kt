package nl.becu.dewi.student656552.articles.domain.repository

import nl.becu.dewi.student656552.articles.domain.models.Article
import nl.becu.dewi.student656552.articles.domain.models.ArticleResponse
import nl.becu.dewi.student656552.articles.util.Resource

interface ArticleRepository {
    suspend fun getArticleById(id: Int, authToken: String?): Resource<Article>

    suspend fun putLikeArticle(articleId: Int, authToken: String): Resource<Unit>

    suspend fun deleteLikeArticle(articleId: Int, authToken: String): Resource<Unit>

    suspend fun getArticleResponse(startingIndex: Int, pageSize: Int, authToken: String?): Resource<Result<ArticleResponse>>

    suspend fun getLikedArticleResponse(authToken: String): Resource<Result<ArticleResponse>>

}