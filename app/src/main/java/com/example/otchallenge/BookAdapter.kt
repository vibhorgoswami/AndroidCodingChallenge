package com.example.otchallenge

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.otchallenge.databinding.ItemBookCardBinding
import com.example.otchallenge.model.Book

class BookAdapter(private val books: List<Book>) : RecyclerView.Adapter<BookAdapter.BookViewHolder>() {

    private lateinit var binding: ItemBookCardBinding

    inner class BookViewHolder(itemBookCardBinding: ItemBookCardBinding):
        RecyclerView.ViewHolder(itemBookCardBinding.root) {
        fun bind(book: Book) {
            binding.bookTitle.text = book.title
            binding.bookDescription.text = book.description
            Glide.with(itemView.context)
                .load(book.imageUrl)
                .placeholder(R.drawable.outline_book_24_placeholder)
                .into(binding.bookImage)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BookViewHolder {
        binding = ItemBookCardBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false,
        )
        return BookViewHolder(binding)
    }

    override fun onBindViewHolder(holder: BookViewHolder, position: Int) {
        val book = books[position]
        holder.bind(book)
    }

    override fun getItemCount(): Int = books.size
}