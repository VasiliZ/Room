package com.github.rtyvz.room.repository

import com.github.rtyvz.room.dao.AuthorDao
import com.github.rtyvz.room.db.entity.AuthorEntity

class AuthorRepository(private val authorDao: AuthorDao) {

    fun getAuthors() = authorDao.getAuthors()

    suspend fun addAuthor(author: AuthorEntity) {
        authorDao.insertAuthor(author)
    }

    fun getAuthorById(authorId: Int) = authorDao.getAuthorById(authorId)
}