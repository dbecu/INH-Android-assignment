package nl.becu.dewi.student656552.articles.domain.util

interface Paginator<Key, Item> {
    suspend fun loadNextItems()
    fun reset()
}