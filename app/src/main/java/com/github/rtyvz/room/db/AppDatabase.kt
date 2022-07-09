package com.github.rtyvz.room.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.github.rtyvz.room.dao.AuthorDao
import com.github.rtyvz.room.dao.BookDao
import com.github.rtyvz.room.db.entity.AuthorEntity
import com.github.rtyvz.room.db.entity.BookEntity

@Database(entities = [AuthorEntity::class, BookEntity::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun bookDao(): BookDao
    abstract fun authorDao(): AuthorDao
}