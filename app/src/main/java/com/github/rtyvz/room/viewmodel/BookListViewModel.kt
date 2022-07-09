package com.github.rtyvz.room.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.github.rtyvz.room.App
import com.github.rtyvz.room.data.BookPresentation
import com.github.rtyvz.room.db.entity.BookEntity
import com.github.rtyvz.room.repository.BookRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class BookListViewModel : ViewModel() {

    private val bookRepository = BookRepository(App.db.bookDao())

    val bookListLiveData = MutableLiveData<List<BookPresentation>>()

    init {
        viewModelScope.launch(Dispatchers.IO) {
            bookRepository.getBooks().collect {
                bookListLiveData.postValue(it.map { book ->
                    BookPresentation(book.id, book.title, book.description, book.authorId)
                })
            }
        }
    }

    fun insertBook(title: String, description: String) {
        viewModelScope.launch(Dispatchers.IO) {
            bookRepository.insertBook(
                BookEntity(
                    title = title, description = description
                )
            )
        }
    }

    fun deleteBook(bookId: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            bookRepository.deleteBook(
                bookId
            )
        }
    }
}