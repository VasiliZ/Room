package com.github.rtyvz.room.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.github.rtyvz.room.db.entity.AuthorEntity
import com.github.rtyvz.room.db.entity.BookEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface BookDao {

    @Query("SELECT * FROM BookEntity")
    fun getBooks(): Flow<List<BookEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertBook(bookEntity: BookEntity)

    @Query("SELECT * FROM BookEntity where id = :bookId")
    suspend fun getBook(bookId: Int): BookEntity

    @Query("DELETE FROM BookEntity where id = :bookId")
    suspend fun deleteBookById(bookId: Int)

    @Query("SELECT * FROM BookEntity where authorId = 0 or authorId = :authorId")
    fun getFilteredBookList(authorId: Int): List<BookEntity>

    @Query("SELECT * FROM BookEntity left join AuthorEntity where BookEntity.authorId = AuthorEntity.id and BookEntity.id = :bookId")
    fun getBookWithAuthor(bookId: Int): Map<BookEntity, AuthorEntity>
}