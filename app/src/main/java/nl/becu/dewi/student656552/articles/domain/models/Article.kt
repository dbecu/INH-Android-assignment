package nl.becu.dewi.student656552.articles.domain.models

import java.util.*

data class Article(
    val Id: Int,
    val Feed: Int,
    val Title: String,
    val Summary: String,
    val Image: String,
    val PublishDate: String, //TODO: CHANGE TO DATE
    val Url: String,
    val Related: List<String>,
    val IsLiked: Boolean,
    val Categories: List<Category>
)

//TODO: ADD CATEGORIES