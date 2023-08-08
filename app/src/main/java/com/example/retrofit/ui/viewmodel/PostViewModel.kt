package com.example.retrofit.ui.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.retrofit.data.model.Post
import com.example.retrofit.data.model.PostItem
import com.example.retrofit.data.repository.PostRepository
import kotlinx.coroutines.launch
import retrofit2.Response

class PostViewModel(private val repository: PostRepository) : ViewModel() {

    val postResponse: MutableLiveData<Response<PostItem>> = MutableLiveData()
    val customPostResponse: MutableLiveData<Response<Post>> = MutableLiveData()
    val pushPostsResponse : MutableLiveData<Response<PostItem>> = MutableLiveData()

    fun getPosts() {
        viewModelScope.launch {
            val response: Response<PostItem> = repository.getPosts()
            postResponse.value = response
        }
    }


    fun getCustomPosts(userId: Int, sort: String, order: String) {
        viewModelScope.launch {
            val response: Response<Post> = repository.getCustomPosts(userId, sort, order)
            customPostResponse.value = response
        }
    }

    fun setPosts(postItem: PostItem) {
        viewModelScope.launch {
            val  response : Response<PostItem> = repository.setPosts(postItem)
            pushPostsResponse.value = response
        }
    }
}