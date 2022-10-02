package nl.becu.dewi.student656552.articles.domain.models

data class ArticleEntity(
    val Id: Int?,
    val Feed: Int?,
    val Title: String?,
    val Summary: String?,
    val Image: String?
)

//title, summary,
//image, full article url (which may reside behind a clickable View).