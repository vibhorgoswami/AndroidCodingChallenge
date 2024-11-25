package com.example.otchallenge.repository

import com.example.otchallenge.model.Book

class BookCacheRepository : BookDataSource {
    private var cachedBooks: List<Book>? = null

    fun saveBooks(books: List<Book>) {
        cachedBooks = books
    }

    override suspend fun getBooks(): List<Book> {
        return cachedBooks ?: emptyList()
    }
}