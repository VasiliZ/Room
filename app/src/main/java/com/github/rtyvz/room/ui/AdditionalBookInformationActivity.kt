package com.github.rtyvz.room.ui

import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.view.isVisible
import androidx.lifecycle.ViewModelProvider
import com.github.rtyvz.room.R
import com.github.rtyvz.room.viewmodel.AdditionalBookInfoViewModel

class AdditionalBookInformationActivity : AppCompatActivity() {

    companion object {
        const val ADDITIONAL_INFORMATION_BOOK_ID_EXTRA = "ADDITIONAL_INFORMATION_BOOK_ID"
    }

    private lateinit var authorName: TextView
    private lateinit var bookTitle: TextView
    private lateinit var toBooksButton: Button
    private lateinit var viewModel: AdditionalBookInfoViewModel
    private lateinit var container: ConstraintLayout
    private lateinit var additionalInfo: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.book_with_author_activity)

        viewModel = ViewModelProvider(this)[AdditionalBookInfoViewModel::class.java]


        viewModel.getAdditionalInformation(
            intent.getIntExtra(
                ADDITIONAL_INFORMATION_BOOK_ID_EXTRA,
                0
            )
        )

        viewModel.book.observe(this) {
            if (it == null) {
                additionalInfo.isVisible = true
                container.isVisible = false
            } else {
                authorName.text = it.authorName
                bookTitle.text = it.bookTitle
            }
        }

        authorName = findViewById(R.id.authorNameTextView)
        bookTitle = findViewById(R.id.titleBookTextView)
        toBooksButton = findViewById(R.id.toBookListButton)
        container = findViewById(R.id.additionalInfoContainer)
        additionalInfo = findViewById(R.id.additionalInfo)

        toBooksButton.setOnClickListener {
            onBackPressed()
        }
    }
}