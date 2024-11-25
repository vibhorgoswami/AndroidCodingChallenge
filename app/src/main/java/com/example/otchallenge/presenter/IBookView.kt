package com.example.otchallenge.presenter

import com.example.otchallenge.model.Book

interface IBookView {
    fun showBooks(books: List<Book>)
    fun showError(message: String)
}