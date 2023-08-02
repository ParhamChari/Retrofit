package com.example.retrofit.ui.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.retrofit.data.model.PostItem
import com.example.retrofit.data.repository.PostRepository
import kotlinx.coroutines.launch
import retrofit2.Response

class PostViewModel (private val repository: PostRepository): ViewModel(){

    val postResponse: MutableLiveData<Response<PostItem>> = MutableLiveData()

    fun getPosts() {
        viewModelScope.launch {
            val response: Response<PostItem> = repository.getPosts()
            postResponse.value = response
        }
    }
}