package com.example.otchallenge.di

import com.example.otchallenge.network.NYTService
import com.example.otchallenge.presenter.BookPresenter
import com.example.otchallenge.repository.BookApiRepository
import com.example.otchallenge.repository.BookCacheRepository
import com.example.otchallenge.repository.BookRepository
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class AppModule {

    @Provides
    @Singleton
    fun provideCacheRepository(): BookCacheRepository {
        return BookCacheRepository()
    }

    @Provides
    @Singleton
    fun provideApiRepository(apiService: NYTService): BookApiRepository {
        return BookApiRepository(apiService)
    }

    @Provides
    @Singleton
    fun provideBookRepository(
        apiRepository: BookApiRepository,
        cacheRepository: BookCacheRepository
    ): BookRepository {
        return BookRepository(apiRepository, cacheRepository)
    }

    @Provides
    fun provideBookPresenter(
        repository: BookRepository,
    ): BookPresenter {
        return BookPresenter(repository)
    }
}