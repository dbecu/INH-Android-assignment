package nl.becu.dewi.student656552.articles.domain.use_case

import nl.becu.dewi.student656552.articles.domain.repository.ArticleRepository

class GetNextId (
    private val repository: ArticleRepository
) {

    suspend operator fun invoke(nextPage: Int, amount: Int): Int {
        return repository.getNextId(nextPage, amount)
    }
}