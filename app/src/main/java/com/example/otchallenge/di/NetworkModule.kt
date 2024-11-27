package com.example.otchallenge.di

import com.example.otchallenge.network.NYTService
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
class NetworkModule {
    @Provides
    @Singleton
    fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(okHttpClient)
            .build()
    }

    @Provides
    @Singleton
    fun provideOkHttpClient(apiKeyProvider: ApiKeyProvider): OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor { chain ->
                val original = chain.request()
                val originalUrl = original.url()

                // Add the API key to the query parameters
                val urlWithApiKey = originalUrl.newBuilder()
                    .addQueryParameter(KEY_NAME, apiKeyProvider.apiKey)
                    .build()

                val requestWithApiKey = original.newBuilder()
                    .url(urlWithApiKey)
                    .build()

                chain.proceed(requestWithApiKey)
            }
            .build()
    }

    @Provides
    @Singleton
    fun provideNYTService(retrofit: Retrofit): NYTService {
        return retrofit.create(NYTService::class.java)
    }

    companion object {
        private const val BASE_URL = "https://api.nytimes.com/svc/books/v3/"
        private const val KEY_NAME = "api-key"
    }
}