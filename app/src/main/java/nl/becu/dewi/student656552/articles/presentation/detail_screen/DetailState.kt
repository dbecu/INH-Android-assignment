package nl.becu.dewi.student656552.articles.presentation.detail_screen

import androidx.compose.material.Icon
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import nl.becu.dewi.student656552.articles.domain.models.Article

data class DetailState(
    val article: Article? = null, //TODO should never be null
    val authToken: String?,
    val isFavedIcon: Boolean
    )