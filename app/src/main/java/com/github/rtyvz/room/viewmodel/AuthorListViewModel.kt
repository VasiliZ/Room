package com.github.rtyvz.room.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.github.rtyvz.room.App
import com.github.rtyvz.room.data.AuthorPresentation
import com.github.rtyvz.room.repository.AuthorRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class AuthorListViewModel : ViewModel() {

    private val authorRepository = AuthorRepository(App.db.authorDao())

    val authorsLiveData = MutableLiveData<List<AuthorPresentation>>()

    init {
        getAuthors()
    }

    private fun getAuthors() {
        viewModelScope.launch(Dispatchers.IO) {
            authorRepository.getAuthors().collect { authorList ->
                authorsLiveData.postValue(authorList.map {
                    AuthorPresentation(
                        id = it.id,
                        name = it.name
                    )
                }
                )
            }
        }
    }
}