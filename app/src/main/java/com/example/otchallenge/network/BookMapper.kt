package com.example.otchallenge.network

import com.example.otchallenge.model.Book
import com.example.otchallenge.network.wrapper.BookDto

object BookMapper {
    private fun mapBookApiResponse(bookApi: BookDto): Book {
        return Book(
            rank = bookApi.rank,
            title = bookApi.title,
            description = bookApi.description,
            imageUrl = bookApi.book_image,
            author = bookApi.author
        )
    }

    fun mapBookListApiResponse(apiBooks: List<BookDto>): List<Book> {
        return apiBooks.map { mapBookApiResponse(it) }
    }
}