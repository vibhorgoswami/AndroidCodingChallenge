package com.example.otchallenge.repository

import com.example.otchallenge.model.Book
import com.example.otchallenge.network.BookMapper
import com.example.otchallenge.network.NYTService

class BookApiRepository(private val apiService: NYTService) : BookDataSource{

    override suspend fun getBooks(): List<Book> {
        val apiBooks = apiService.getBooks().results.books
        return BookMapper.mapBookListApiResponse(apiBooks)
    }
}