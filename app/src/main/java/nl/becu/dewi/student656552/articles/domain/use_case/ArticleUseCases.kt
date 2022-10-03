package nl.becu.dewi.student656552.articles.domain.use_case

data class ArticleUseCases (
    val getArticle: GetArticle,
    val getArticles: GetArticles,
    val getResultArticles: GetResultArticles,
    val getNextId: GetNextId
)