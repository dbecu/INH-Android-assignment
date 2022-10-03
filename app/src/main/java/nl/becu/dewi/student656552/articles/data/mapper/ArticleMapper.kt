package nl.becu.dewi.student656552.articles.data.mapper

import nl.becu.dewi.student656552.articles.domain.models.Article
import nl.becu.dewi.student656552.articles.domain.models.ArticleEntity
import nl.becu.dewi.student656552.articles.domain.models.ArticleResponse
import nl.becu.dewi.student656552.articles.domain.models.ArticleResponseEntity
import java.text.SimpleDateFormat

class ArticleMapper {

    val dateFormatter = SimpleDateFormat("yyyy-MM-dd")

    fun mapArticleResponse(entity: ArticleResponseEntity): Result<ArticleResponse> = runCatching {
        with(entity) {
            ArticleResponse(
                Results = mapEntityArticles(Results!!),
                NextId = NextId!!
            )
        }
    }

    private fun mapEntityArticles(entities: List<ArticleEntity>): List<Article> {
        val articles = mutableListOf<Article>()

        for(entity in entities)
        {
            articles.add(Article(
                Id = entity.Id!!,
                Feed = entity.Feed!!,
                Title = entity.Title!!,
                Summary = entity.Summary!!,
                Image = entity.Image!!,
                PublishDate = dateFormatter.parse(entity.PublishDate!!),
                Url = entity.Url!!,
                Related = entity.Related!!,
                IsLiked = entity.IsLiked!!,
                Categories = entity.Categories!!
            ))
        }

        return articles
    }

    fun mapArticles(entity: ArticleResponseEntity): Result<List<Article>> = runCatching {
        entity.Results?.map {
            with(it) {
                Article(
                    Id = Id!!,
                    Feed = Feed!!,
                    Title = Title!!,
                    Summary = Summary!!,
                    Image = Image!!,
                    PublishDate = dateFormatter.parse(PublishDate!!),
                    Url = Url!!,
                    Related = Related!!,
                    IsLiked = IsLiked!!,
                    Categories = Categories!!
                )
            }
        } ?: emptyList()
    }

    fun mapFirstArticle(entity: ArticleResponseEntity): Result<Article> = runCatching {
        with(entity.Results) {
            Article(
                Id = this?.get(0)?.Id!!,
                Feed = this?.get(0)?.Feed!!,
                Title = this?.get(0)?.Title!!,
                Summary = this?.get(0)?.Summary!!,
                Image = this?.get(0)?.Image!!,
                PublishDate = dateFormatter.parse(this?.get(0)?.PublishDate!!),
                Url = this?.get(0)?.Url!!,
                Related = this?.get(0)?.Related!!,
                IsLiked = this?.get(0)?.IsLiked!!,
                Categories = this?.get(0)?.Categories!!
            )
        }
    }

    fun mapResponseEntityToArticles(entity: ArticleResponseEntity?): List<Article>{
        return entity?.Results?.map {
            with(it) {
                Article(
                    Id = Id!!,
                    Feed = Feed!!,
                    Title = Title!!,
                    Summary = Summary!!,
                    Image = Image!!,
                    PublishDate = dateFormatter.parse(PublishDate!!),
                    Url = Url!!,
                    Related = Related!!,
                    IsLiked = IsLiked!!,
                    Categories = Categories!!
                )
            }
        } ?: emptyList()
    }
}