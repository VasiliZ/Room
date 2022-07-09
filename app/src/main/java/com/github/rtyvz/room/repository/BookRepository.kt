package com.github.rtyvz.room.repository

import com.github.rtyvz.room.dao.BookDao
import com.github.rtyvz.room.db.entity.BookEntity

class BookRepository(private val bookDao: BookDao) {

    suspend fun insertBook(bookEntity: BookEntity) {
        bookDao.insertBook(bookEntity)
    }

    fun getBooks() = bookDao.getBooks()

    suspend fun deleteBook(bookId: Int) {
        bookDao.deleteBookById(bookId)
    }

    suspend fun updateBooks(bookList: List<BookEntity>) {
        bookList.forEach {
            bookDao.insertBook(it)
        }
    }
}