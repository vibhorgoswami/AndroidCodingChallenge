package com.example.otchallenge.repository

import com.example.otchallenge.model.Book

class BookRepository(
    private val apiRepository: BookApiRepository,
    private val cacheRepository: BookCacheRepository
) {

    suspend fun getBooks(): List<Book> {
        val cachedBooks = cacheRepository.getBooks()
        return cachedBooks.ifEmpty {
            val apiBooks = apiRepository.getBooks()
            cacheRepository.saveBooks(apiBooks) // Save to cache
            apiBooks
        }
    }
}