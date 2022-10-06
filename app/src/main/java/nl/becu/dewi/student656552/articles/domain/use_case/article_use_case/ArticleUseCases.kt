package nl.becu.dewi.student656552.articles.domain.use_case.article_use_case

data class ArticleUseCases (
    val getArticle: GetArticle,
    val getArticles: GetArticles,
    val getNextId: GetNextId,
    val getArticleResponse: GetArticleResponse,
    val putLikeArticle: PutLikeArticle,
    val deleteLikeArticle: DeleteLikeArticle
)