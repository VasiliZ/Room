package com.github.rtyvz.room.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.github.rtyvz.room.R
import com.github.rtyvz.room.adapter.AuthorsBooksAdapter
import com.github.rtyvz.room.viewmodel.AddAuthorsBooksViewModel

class AddAuthorsBooksActivity : AppCompatActivity() {

    private lateinit var viewModel: AddAuthorsBooksViewModel
    private lateinit var authorsBooksAdapter: AuthorsBooksAdapter

    companion object {
        const val AUTHOR_ID_EXTRA = "AUTHOR_ID"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.author_and_books_activity)

        viewModel = ViewModelProvider(this)[AddAuthorsBooksViewModel::class.java]
        authorsBooksAdapter = AuthorsBooksAdapter()
        val authorId = intent.getStringExtra(AUTHOR_ID_EXTRA) ?: ""
    }
}