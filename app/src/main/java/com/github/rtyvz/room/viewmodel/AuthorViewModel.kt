package com.github.rtyvz.room.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.github.rtyvz.room.App
import com.github.rtyvz.room.db.entity.AuthorEntity
import com.github.rtyvz.room.repository.AuthorRepository
import kotlinx.coroutines.launch

class AuthorViewModel : ViewModel() {
    private val authorRepository = AuthorRepository(App.db.authorDao())

    fun addAuthor(authorEntity: AuthorEntity) {
        viewModelScope.launch {
            authorRepository.addAuthor(authorEntity)
        }
    }
}