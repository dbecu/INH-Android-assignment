package nl.becu.dewi.student656552.articles.data.repository

import nl.becu.dewi.student656552.articles.data.data_source.ArticleApi
import nl.becu.dewi.student656552.articles.data.mapper.ArticleMapper
import nl.becu.dewi.student656552.articles.domain.models.Article
import nl.becu.dewi.student656552.articles.domain.models.ArticleResponse
import nl.becu.dewi.student656552.articles.domain.repository.ArticleRepository
import java.text.SimpleDateFormat
import java.time.LocalDateTime

class ArticleRepositoryImpl(
    private val api: ArticleApi
) : ArticleRepository {

    private val mapper = ArticleMapper()

    override suspend fun getArticles(): List<Article> {
        //startKey: Int, loadSize: Int

        val articleResponse = api.getArticles(10)

        val formatter = SimpleDateFormat("yyyy-MM-dd")

        return articleResponse.body()?.Results?.map {
            with(it) {
                Article(
                    Id = Id!!,
                    Feed = Feed!!,
                    Title = Title!!,
                    Summary = Summary!!,
                    Image = Image!!,
                    PublishDate = formatter.parse(PublishDate!!),
                    Url = Url!!,
                    Related = Related!!,
                    IsLiked = IsLiked!!,
                    Categories = Categories!!
                )
            }
        } ?: emptyList()

        /*
        val response = api.getArticles(2)
        val articles = when {
            response.isSuccessful -> {
                val body = response.body()
                if (body != null){
                    articleMapper.mapArticles(body)
                } else{
                    Result.failure(java.lang.IllegalStateException("Body was empty"))
                }
            }
            else -> Result.failure(java.lang.IllegalStateException("Something went wrong"))
        }

        return articles.getOrDefault(emptyList())

         */
    }

    // TODO: CHANGE THIS LOGIC IS IN THE WRONG LAYER
    override suspend fun getArticleById(id: Int): Article {
        val articleResponse = api.getArticleById(id)
        return articleResponse.body()?.let { mapper.mapFirstArticle(it).getOrThrow() } ?: throw Exception()
    }


    private suspend fun getArticleResponseById(id: Int): ArticleResponse? {
        /*
        val response = api.getArticleById(id)

        val result = when {
            response.isSuccessful -> {
                val body = response.body()
                if (body != null){
                    articleMapper.mapArticleResponse(body)
                } else{
                    Result.failure(java.lang.IllegalStateException("Body was empty"))
                }
            }
            else -> Result.failure(java.lang.IllegalStateException("Something went wrong"))
        }

        return result.getOrNull()

         */

        return ArticleResponse(emptyList(), 1)
    }

}