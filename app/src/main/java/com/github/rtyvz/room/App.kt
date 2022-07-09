package com.github.rtyvz.room

import android.app.Application
import androidx.room.Room
import com.github.rtyvz.room.db.AppDatabase

private const val APP_DB_NAME = "books_db"

class App : Application() {

    companion object {
        lateinit var db: AppDatabase
            private set
    }

    override fun onCreate() {
        super.onCreate()

        db = Room.databaseBuilder(applicationContext, AppDatabase::class.java, APP_DB_NAME).build()

    }
}