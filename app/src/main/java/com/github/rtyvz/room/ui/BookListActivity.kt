package com.github.rtyvz.room.ui

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView
import com.github.rtyvz.room.R
import com.github.rtyvz.room.adapter.BookListAdapter
import com.github.rtyvz.room.viewmodel.BookListViewModel

class BookListActivity : AppCompatActivity() {

    private lateinit var viewModel: BookListViewModel
    private lateinit var bottomSheet: ConstraintLayout
    private lateinit var title: TextView
    private lateinit var description: TextView
    private lateinit var addBookButton: Button
    private lateinit var bookListAdapter: BookListAdapter
    private lateinit var bookList: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        bottomSheet = findViewById(R.id.bottomSheet)
        title = bottomSheet.findViewById(R.id.bookTitleTextViewBottomSheet)
        description = bottomSheet.findViewById(R.id.bookDescriptionTextViewBottomSheet)
        addBookButton = bottomSheet.findViewById(R.id.addBookButton)
        bookList = findViewById(R.id.bookList)

        bookListAdapter = BookListAdapter {
            viewModel.deleteBook(it)
        }
        bookList.adapter = bookListAdapter
        viewModel = ViewModelProvider(this)[BookListViewModel::class.java]

        viewModel.bookListLiveData.observe(this) {
            bookListAdapter.submitList(it)
        }

        addBookButton.setOnClickListener {
            viewModel.insertBook(title.text.toString(), description.text.toString())
            clearInputFields()
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.add_author_item -> startActivity(Intent(this, AuthorListActivity::class.java))
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.book_menu, menu)
        return true
    }

    private fun clearInputFields() {
        title.text = ""
        description.text = ""
    }
}