package com.github.rtyvz.room.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
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
}