package nl.becu.dewi.student656552.data.repository

import nl.becu.dewi.student656552.data.data_source.ArticleApi
import nl.becu.dewi.student656552.data.mapper.ArticleMapper
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.create

open abstract class BaseRepository {
    private val baseUrl: String = "https://inhollandbackend.azurewebsites.net/api/"

    private val retrofit = Retrofit.Builder()
        .baseUrl(baseUrl)
        .addConverterFactory(MoshiConverterFactory.create())
        .build()

    protected val api: ArticleApi = retrofit.create<ArticleApi>()

    protected val articleMapper: ArticleMapper = ArticleMapper()
}