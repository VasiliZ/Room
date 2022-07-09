package com.github.rtyvz.room.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.github.rtyvz.room.db.entity.AuthorEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface AuthorDao {

    @Query("SELECT * FROM AuthorEntity")
    fun getAuthors(): Flow<List<AuthorEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAuthor(bookEntity: AuthorEntity)

}