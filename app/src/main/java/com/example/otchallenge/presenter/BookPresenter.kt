package com.example.otchallenge.presenter

import com.example.otchallenge.repository.BookRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

class BookPresenter @Inject constructor(
    private val repository: BookRepository,
    private val coroutineScope: CoroutineScope = CoroutineScope(Job() + Dispatchers.IO),
) {
    private var view: IBookView? = null

    fun attachView(view: IBookView) {
        this.view = view
    }

    fun detachView() {
        this.view = null
    }

    fun fetchBooks() {
        coroutineScope.launch {
            try {
                val books = repository.getBooks()
                withContext(Dispatchers.Main) {
                    view?.showBooks(books)
                }
            } catch (exception: Exception) {
                withContext(Dispatchers.Main) {
                    view?.showError(exception.message ?: "Unknown error")
                }
            }
        }
    }
}