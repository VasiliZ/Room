package com.github.rtyvz.room.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.github.rtyvz.room.App
import com.github.rtyvz.room.data.BookWithAuthorPresentation
import com.github.rtyvz.room.repository.BookRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class AdditionalBookInfoViewModel : ViewModel() {

    private val bookRepository = BookRepository(App.db.bookDao())
    val book = MutableLiveData<BookWithAuthorPresentation?>()

    fun getAdditionalInformation(bookId: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            val bookWithAuthor = bookRepository.getBookWithAuthor(bookId)
            if (bookWithAuthor.isNotEmpty()) {
                bookWithAuthor.map {
                    book.postValue(BookWithAuthorPresentation(it.key.title, it.value.name))
                }
            } else {
                book.postValue(null)
            }
        }
    }
}