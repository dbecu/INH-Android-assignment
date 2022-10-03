package nl.becu.dewi.student656552.articles.data.mapper

import nl.becu.dewi.student656552.articles.domain.models.Article
import nl.becu.dewi.student656552.articles.domain.models.ArticleResponse
import nl.becu.dewi.student656552.articles.domain.models.ArticleResponseEntity

class ArticleMapper {
    /*
    fun map(entity: ArticleEntity): Result<Article> = runCatching {
        with(entity) {
            Article(
                Results = results?.map{mapResult(it).getOrThrow()}.orEmpty(),
                IsLiked = IsLiked!!,
                NextId = NextId!!
            )
        }
    }
     */

    fun mapArticleResponse(entity: ArticleResponseEntity): Result<ArticleResponse> = runCatching {
        with(entity) {
            ArticleResponse(
                Results = Results!!,
                NextId = NextId!!
            )
        }
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
                    PublishDate = PublishDate!!,
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
                PublishDate = this?.get(0)?.PublishDate!!,
                Url = this?.get(0)?.Url!!,
                Related = this?.get(0)?.Related!!,
                IsLiked = this?.get(0)?.IsLiked!!,
                Categories = this?.get(0)?.Categories!!
            )
        }
    }
}