package nl.becu.dewi.student656552.articles.presentation.detail_screen

import nl.becu.dewi.student656552.articles.presentation.register_screen.RegisterEvent

sealed class DetailEvent {
    data class PutArticleLike(val articleId: Int, val authToken: String): DetailEvent()
    data class DeleteArticleLike(val articleId: Int, val authToken: String): DetailEvent()
    data class GetAuthToken(val authToken: String): DetailEvent()

}