package nl.becu.dewi.student656552.domain.use_case

import nl.becu.dewi.student656552.domain.models.Article
import nl.becu.dewi.student656552.domain.repository.ArticleRepository

class GetResultArticles (
    private val repository: ArticleRepository
) {

        suspend operator fun invoke(): Result<List<Article>> {
            return repository.getArticles()
        }
    }