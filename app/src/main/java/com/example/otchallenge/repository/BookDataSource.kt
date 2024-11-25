package com.example.otchallenge.repository

import com.example.otchallenge.model.Book

interface BookDataSource {
    suspend fun getBooks(): List<Book>
}