package com.example.retrofit.ui.viewmodel.factory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.retrofit.data.repository.PostRepository
import com.example.retrofit.ui.viewmodel.PostViewModel

@Suppress("UNCHECKED_CAST")
class PostViewModelFactory (private val repository: PostRepository) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return PostViewModel(repository) as T
    }
}