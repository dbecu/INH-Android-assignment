package nl.becu.dewi.student656552.articles.domain.models

data class Article(
    val Id: Int,
    val Feed: Int,
    val Title: String,
    val Summary: String,
    val Image: String
)