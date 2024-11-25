package com.example.otchallenge.repository

import com.example.otchallenge.model.Book
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test

class BookRepositoryTest {

    private lateinit var apiRepository: BookApiRepository
    private lateinit var cacheRepository: BookCacheRepository
    private lateinit var bookRepository: BookRepository
    private val testDispatcher = StandardTestDispatcher()

    @Before
    fun setUp() {
        apiRepository = mockk()
        cacheRepository = BookCacheRepository()
        bookRepository = BookRepository(apiRepository, cacheRepository)
    }

    @Test
    fun `test getBooks uses cache when available`() = runTest(testDispatcher) {
        val cachedBooks =
            listOf(
                Book(
                    rank = 1,
                    title = "Book 1",
                    description = "Description 1",
                    imageUrl = "image1.jpg",
                    author = "John Doe",
                )
            )
        cacheRepository.saveBooks(cachedBooks)

        val books = bookRepository.getBooks()

        assert(cachedBooks == books)
    }

    @Test
    fun `test getBooks fetches from API when cache is empty`() = runTest(testDispatcher) {
        val apiBooks =
            listOf(
                Book(
                    rank = 1,
                    title = "Book 1",
                    description = "Description 1",
                    imageUrl = "image1.jpg",
                    author = "John Doe",
                )
            )
        coEvery { apiRepository.getBooks() } returns apiBooks

        val books = bookRepository.getBooks()

        assert(apiBooks == books)
    }
}