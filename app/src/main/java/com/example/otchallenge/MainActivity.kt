package com.example.otchallenge

import android.os.Bundle
import android.util.Log
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.otchallenge.databinding.ActivityMainBinding
import com.example.otchallenge.model.Book
import com.example.otchallenge.presenter.BookPresenter
import com.example.otchallenge.presenter.IBookView
import javax.inject.Inject

class MainActivity : AppCompatActivity(), IBookView {

	@Inject
	lateinit var presenter: BookPresenter

	private lateinit var adapter: BookAdapter
	private val bookList = mutableListOf<Book>()
	private lateinit var binding: ActivityMainBinding

	override fun onCreate(savedInstanceState: Bundle?) {
		(application as MyApplication).appComponent.inject(this)
		super.onCreate(savedInstanceState)
		enableEdgeToEdge()
		binding = ActivityMainBinding.inflate(layoutInflater)
		setContentView(binding.root)

		binding.recyclerView.layoutManager = LinearLayoutManager(this)
		adapter = BookAdapter(bookList)
		binding.recyclerView.adapter = adapter

		// Setup Swipe to Refresh
		binding.swipeRefreshLayout.setOnRefreshListener {
			presenter.fetchBooks()
		}

		// Attach presenter
		presenter.attachView(this)
		presenter.fetchBooks()
	}

	override fun showBooks(books: List<Book>) {
		bookList.clear()
		bookList.addAll(books)
		adapter.notifyDataSetChanged()
		binding.recyclerView.isVisible = true
		binding.emptyStateLayout.isVisible = false
		binding.swipeRefreshLayout.isRefreshing = false
	}

	override fun showError(message: String) {
		binding.swipeRefreshLayout.isRefreshing = false
		Log.e(TAG, "Error showing list of books. Failed with $message")
		binding.recyclerView.isVisible = false
		binding.emptyStateLayout.isVisible = true
		binding.errorText.text = message
	}

	override fun onDestroy() {
		super.onDestroy()
		presenter.detachView()
	}

	companion object {
		private val TAG = MainActivity::class.java.simpleName
	}
}
