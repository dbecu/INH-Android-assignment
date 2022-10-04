package nl.becu.dewi.student656552.di

import android.app.Application
import android.content.Context
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import nl.becu.dewi.student656552.articles.data.data_source.ArticleApi
import nl.becu.dewi.student656552.articles.data.data_source.UserApi
import nl.becu.dewi.student656552.articles.data.repository.ArticleRepositoryImpl
import nl.becu.dewi.student656552.articles.data.repository.UserRepositoryImpl
import nl.becu.dewi.student656552.articles.domain.repository.ArticleRepository
import nl.becu.dewi.student656552.articles.domain.repository.UserRepository
import nl.becu.dewi.student656552.articles.domain.use_case.*
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
class AppModule {

    private var BASE_URL = "https://inhollandbackend.azurewebsites.net/api/"

    private var httpClient: OkHttpClient.Builder = OkHttpClient.Builder()
        .callTimeout(2, TimeUnit.MINUTES)
        .connectTimeout(20, TimeUnit.SECONDS)
        .readTimeout(30, TimeUnit.SECONDS)
        .writeTimeout(30, TimeUnit.SECONDS)

    @Provides
    @Singleton
    fun provideArticleApi(app: Application): ArticleApi {
        val builder = Retrofit.Builder()
            .baseUrl(BASE_URL + "Articles/")
            .addConverterFactory(MoshiConverterFactory.create())

        builder.client(httpClient.build());

        return builder
            .build()
            .create(ArticleApi::class.java)
    }


    @Provides
    @Singleton
    fun provideUserApi(app: Application): UserApi {
        val builder = Retrofit.Builder()
            .baseUrl(BASE_URL + "Users/")
            .addConverterFactory(MoshiConverterFactory.create())

        builder.client(httpClient.build());

        return builder
            .build()
            .create(UserApi::class.java)
    }

    @Provides
    @Singleton
    fun provideArticleRepository(api: ArticleApi): ArticleRepository {
        return ArticleRepositoryImpl(api)
    }

    @Provides
    @Singleton
    fun provideUserRepository(api: UserApi): UserRepository {
        return UserRepositoryImpl(api)
    }

    @Provides
    @Singleton
    fun provideArticleUseCases(repository: ArticleRepository): ArticleUseCases {
        return ArticleUseCases(
            getArticle = GetArticle(repository),
            getNextId = GetNextId(repository),
            getResultArticles = GetResultArticles(repository),
            getLikedArticles = GetLikedArticles(repository),
            getAllArticlesWithLike = GetAllArticlesWithLike(repository),
            putLikeArticle = PutLikeArticle(repository),
            deleteLikeArticle = DeleteLikeArticle(repository)
        )
    }

    @Provides
    @Singleton
    fun provideUserUseCases(repository: UserRepository): UserUseCase {
        return UserUseCase(
            login = Login(repository),
            register = Register(repository)
        )
    }
}