package com.github.rtyvz.room.ui

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView
import com.github.rtyvz.room.R
import com.github.rtyvz.room.adapter.AuthorListAdapter
import com.github.rtyvz.room.viewmodel.AuthorListViewModel
import com.google.android.material.floatingactionbutton.FloatingActionButton

class AuthorListActivity : AppCompatActivity() {

    private lateinit var authorList: RecyclerView
    private lateinit var addAuthorFab: FloatingActionButton
    private lateinit var authorListAdapter: AuthorListAdapter
    private lateinit var viewModel: AuthorListViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.author_list_activity)

        viewModel = ViewModelProvider(this)[AuthorListViewModel::class.java]

        authorListAdapter = AuthorListAdapter {
            startActivity(Intent(this, AddAuthorsBooksActivity::class.java).apply {
                putExtra(AddAuthorsBooksActivity.AUTHOR_ID_EXTRA, it)
            })
        }

        authorList = findViewById(R.id.authorList)
        addAuthorFab = findViewById(R.id.addAuthorFab)

        authorList.adapter = authorListAdapter

        viewModel.authorsLiveData.observe(this) {
            authorListAdapter.submitList(it)
        }

        addAuthorFab.setOnClickListener {
            startActivity(Intent(this, AuthorActivity::class.java))
        }
    }
}