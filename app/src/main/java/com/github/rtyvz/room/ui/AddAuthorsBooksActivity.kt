package com.github.rtyvz.room.ui

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView
import com.github.rtyvz.room.R
import com.github.rtyvz.room.adapter.AuthorsBooksAdapter
import com.github.rtyvz.room.viewmodel.AddAuthorsBooksViewModel

class AddAuthorsBooksActivity : AppCompatActivity() {

    private lateinit var viewModel: AddAuthorsBooksViewModel
    private lateinit var authorsBooksAdapter: AuthorsBooksAdapter
    private lateinit var authorName: TextView
    private lateinit var bookList: RecyclerView

    companion object {
        const val AUTHOR_ID_EXTRA = "AUTHOR_ID"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.author_and_books_activity)

        val authorId = intent.getIntExtra(AUTHOR_ID_EXTRA, 0)

        viewModel = ViewModelProvider(this)[AddAuthorsBooksViewModel::class.java]

        authorName = findViewById(R.id.authorWithBooks)
        bookList = findViewById(R.id.booksForAuthor)
        authorsBooksAdapter = AuthorsBooksAdapter { id, book ->
            viewModel.updateBook(id, authorId, book)
        }

        bookList.adapter = authorsBooksAdapter

        viewModel.getAuthorById(authorId)

        viewModel.getBooks(authorId)

        viewModel.author.observe(this) {
            authorName.text = it.name
        }

        viewModel.bookList.observe(this) {
            authorsBooksAdapter.submitList(it)
        }
    }
}