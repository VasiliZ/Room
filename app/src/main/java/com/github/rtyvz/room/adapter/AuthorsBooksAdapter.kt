package com.github.rtyvz.room.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.github.rtyvz.room.R
import com.github.rtyvz.room.data.BookPresentation

class AuthorsBooksAdapter :
    ListAdapter<BookPresentation, AuthorsBooksAdapter.AuthorsBookItemHolder>(ItemCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AuthorsBookItemHolder {
        return AuthorsBookItemHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.select_book_item, parent, false)
        )
    }

    override fun onBindViewHolder(holder: AuthorsBookItemHolder, position: Int) {
        holder.bind(currentList[position])
    }

    class AuthorsBookItemHolder(view: View) : RecyclerView.ViewHolder(view) {

        private val checkBox = view.findViewById<CheckBox>(R.id.selectBookCheckBox)
        private val bookTitle = view.findViewById<TextView>(R.id.selectBookTitle)

        fun bind(book: BookPresentation) {
            checkBox.isSelected = book.authorId != 0
            bookTitle.text = book.title
        }
    }
}

class ItemCallback() : DiffUtil.ItemCallback<BookPresentation>() {
    override fun areItemsTheSame(oldItem: BookPresentation, newItem: BookPresentation): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: BookPresentation, newItem: BookPresentation): Boolean {
        return oldItem == newItem
    }
}