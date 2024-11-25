package com.example.otchallenge.presenter

import com.example.otchallenge.model.Book
import com.example.otchallenge.repository.BookRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.Job
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.mockito.ArgumentMatchers.anyList
import org.mockito.ArgumentMatchers.anyString
import org.mockito.Mock
import org.mockito.Mockito.never
import org.mockito.Mockito.verify
import org.mockito.Mockito.`when`
import org.mockito.MockitoAnnotations

@ExperimentalCoroutinesApi
class BookPresenterTest {

    @Mock
    lateinit var view: IBookView

    @Mock
    lateinit var repository: BookRepository

    private lateinit var presenter: BookPresenter
    private val testDispatcher = UnconfinedTestDispatcher()

    @OptIn(ExperimentalCoroutinesApi::class)
    @Before
    fun setUp() {
        MockitoAnnotations.openMocks(this)
        Dispatchers.setMain(testDispatcher)
        presenter =
            BookPresenter(
                repository,
                CoroutineScope(Job() + testDispatcher),
            )
        presenter.attachView(view)
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
        presenter.detachView()
    }

    @Test
    fun `fetchBooks should display books when successful`() = runTest {
        // Arrange
        val books =
            listOf(
                Book(
                    rank = 1,
                    title = "Book 1",
                    description = "Description 1",
                    imageUrl = "image1.jpg",
                    author = "John Doe",
                )
            )
        `when`(repository.getBooks()).thenReturn(books)

        // Act
        presenter.fetchBooks()

        // Assert
        verify(view).showBooks(books)
        verify(view, never()).showError(anyString())
    }

    @Test
    fun `fetchBooks should show error when API call fails`() = runTest {
        // Arrange
        val errorMessage = "Network Error"
        `when`(repository.getBooks()).thenThrow(RuntimeException(errorMessage))

        // Act
        presenter.fetchBooks()

        // Assert
        verify(view).showError(errorMessage)
        verify(view, never()).showBooks(anyList())
    }
}