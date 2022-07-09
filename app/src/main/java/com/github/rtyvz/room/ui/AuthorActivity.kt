package com.github.rtyvz.room.ui

import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.github.rtyvz.room.R
import com.github.rtyvz.room.db.entity.AuthorEntity
import com.github.rtyvz.room.viewmodel.AuthorViewModel
import com.google.android.material.textfield.TextInputEditText

class AuthorActivity : AppCompatActivity() {

    private lateinit var viewModel: AuthorViewModel
    private lateinit var authorName: TextInputEditText
    private lateinit var addAuthorButton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.author_activity)

        viewModel = ViewModelProvider(this)[AuthorViewModel::class.java]
        authorName = findViewById(R.id.authorName)
        addAuthorButton = findViewById(R.id.addAuthorButton)

        addAuthorButton.setOnClickListener {
            viewModel.addAuthor(
                AuthorEntity(
                    name = authorName.text.toString()
                )
            )

            onBackPressed()
        }
    }
}