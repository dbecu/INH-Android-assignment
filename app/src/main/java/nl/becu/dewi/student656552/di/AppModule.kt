package nl.becu.dewi.student656552.di

import android.app.Application
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import nl.becu.dewi.student656552.articles.data.data_source.ArticleApi
import nl.becu.dewi.student656552.articles.data.data_source.UserApi
import nl.becu.dewi.student656552.articles.data.repository.ArticleRepositoryImpl
import nl.becu.dewi.student656552.articles.domain.repository.ArticleRepository
import nl.becu.dewi.student656552.articles.domain.use_case.*
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class AppModule {
    @Provides
    @Singleton
    fun provideArticleApi(app: Application): ArticleApi {
        return Retrofit.Builder()
            .baseUrl("https://inhollandbackend.azurewebsites.net/api/")
            .addConverterFactory(MoshiConverterFactory.create())
            .build()
            .create(ArticleApi::class.java)
    }

    @Provides
    @Singleton
    fun provideArticleRepository(api: ArticleApi): ArticleRepository {
        return ArticleRepositoryImpl(api)
    }

    @Provides
    @Singleton
    fun provideArticleUseCases(repository: ArticleRepository): ArticleUseCases {
        return ArticleUseCases(
            getArticles = GetArticles(repository),
            getArticle = GetArticle(repository),
            getNextId = GetNextId(repository),
            getResultArticles = GetResultArticles(repository)
        )
    }
}