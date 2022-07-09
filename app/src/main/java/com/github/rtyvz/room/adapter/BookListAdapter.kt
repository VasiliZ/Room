package com.github.rtyvz.room.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.github.rtyvz.room.R
import com.github.rtyvz.room.data.BookPresentation

class BookListAdapter(
    private val deleteBookCallback: (Int) -> Unit
) :
    ListAdapter<BookPresentation, BookListAdapter.BookItemViewHolder>(BookDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BookItemViewHolder {
        return BookItemViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.book_item, parent, false)
        ).apply {
            this.itemView.findViewById<ImageView>(R.id.deleteBookAction).setOnClickListener {
                deleteBookCallback(currentList[adapterPosition].id)
            }
        }
    }

    override fun onBindViewHolder(holder: BookItemViewHolder, position: Int) {
        holder.bind(currentList[position])
    }

    class BookItemViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private val bookTitle = view.findViewById<TextView>(R.id.bookTitleTextView)
        private val bookDescription = view.findViewById<TextView>(R.id.bookDescriptionTextView)

        fun bind(book: BookPresentation) {
            bookTitle.text = book.title
            bookDescription.text = book.description
        }
    }
}

class BookDiffCallback : DiffUtil.ItemCallback<BookPresentation>() {
    override fun areItemsTheSame(oldItem: BookPresentation, newItem: BookPresentation): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: BookPresentation, newItem: BookPresentation): Boolean {
        return oldItem == newItem
    }
}