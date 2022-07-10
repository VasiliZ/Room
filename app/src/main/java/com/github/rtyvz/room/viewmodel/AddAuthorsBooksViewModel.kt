package com.github.rtyvz.room.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.github.rtyvz.room.App
import com.github.rtyvz.room.data.AuthorPresentation
import com.github.rtyvz.room.data.BookPresentation
import com.github.rtyvz.room.db.entity.BookEntity
import com.github.rtyvz.room.repository.AuthorRepository
import com.github.rtyvz.room.repository.BookRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

private const val RESET_AUTHOR_VALUE = 0

class AddAuthorsBooksViewModel : ViewModel() {
    private val bookRepository = BookRepository(App.db.bookDao())
    private val authorRepository = AuthorRepository(App.db.authorDao())

    val author = MutableLiveData<AuthorPresentation>()
    val bookList = MutableLiveData<List<BookPresentation>>()

    fun getAuthorById(id: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            val authorEntity = authorRepository.getAuthorById(id)
            author.postValue(AuthorPresentation(authorEntity.id, authorEntity.name))
        }
    }

    fun getBooks(authorId: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            bookList.postValue(bookRepository.getFilteredBooks(authorId).map {
                BookPresentation(it.id, it.title, it.description, it.authorId)
            })
        }
    }

    fun updateBook(isBookSelected: Boolean, authorId: Int, book: BookPresentation) {
        viewModelScope.launch(Dispatchers.IO) {
            if (isBookSelected) {
                updateBook(authorId, book)
            } else {
                updateBook(RESET_AUTHOR_VALUE, book)
            }
        }
    }

    private suspend fun updateBook(authorId: Int, book: BookPresentation) {
        bookRepository.updateBooks(
            with(book) {
                BookEntity(
                    id, title, description, authorId
                )
            }
        )
    }
}