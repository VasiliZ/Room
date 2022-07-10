package com.github.rtyvz.room.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.github.rtyvz.room.R
import com.github.rtyvz.room.data.BookPresentation

class AuthorsBooksAdapter(private val onBookCheckCallback: (Boolean, BookPresentation) -> Unit) :
    ListAdapter<BookPresentation, AuthorsBooksAdapter.AuthorsBookItemHolder>(ItemCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AuthorsBookItemHolder {
        return AuthorsBookItemHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.select_book_item, parent, false)
        ).apply {
            this.itemView.findViewById<CheckBox>(R.id.selectBookCheckBox)
                .setOnCheckedChangeListener { _, b ->
                    onBookCheckCallback(b, currentList[adapterPosition])
                }
        }
    }

    override fun onBindViewHolder(holder: AuthorsBookItemHolder, position: Int) {
        holder.bind(currentList[position])
    }

    class AuthorsBookItemHolder(view: View) : RecyclerView.ViewHolder(view) {

        private val checkBox = view.findViewById<CheckBox>(R.id.selectBookCheckBox)

        fun bind(book: BookPresentation) {
            checkBox.isChecked = book.authorId != 0
            checkBox.text = book.title
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