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
import com.github.rtyvz.room.data.AuthorPresentation

class AuthorListAdapter(private val editAuthorCallback: (Int) -> Unit) :
    ListAdapter<AuthorPresentation, AuthorListAdapter.AuthorItemStateHolder>(AuthorDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AuthorItemStateHolder {
        return AuthorItemStateHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.author_item, parent, false)
        ).apply {
            this.itemView.findViewById<ImageView>(R.id.editAuthor).setOnClickListener {
                editAuthorCallback(currentList[adapterPosition].id)
            }
        }
    }

    override fun onBindViewHolder(holder: AuthorItemStateHolder, position: Int) {
        holder.bind(currentList[position])
    }

    class AuthorItemStateHolder(view: View) : RecyclerView.ViewHolder(view) {

        private val authorName = view.findViewById<TextView>(R.id.authorNameTextView)

        fun bind(author: AuthorPresentation) {
            authorName.text = author.name
        }
    }
}

class AuthorDiffCallback : DiffUtil.ItemCallback<AuthorPresentation>() {
    override fun areItemsTheSame(
        oldItem: AuthorPresentation,
        newItem: AuthorPresentation
    ): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(
        oldItem: AuthorPresentation,
        newItem: AuthorPresentation
    ): Boolean {
        return oldItem == newItem
    }
}