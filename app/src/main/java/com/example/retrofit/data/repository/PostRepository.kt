package com.example.retrofit.data.repository

import com.example.retrofit.data.model.PostItem
import com.example.retrofit.data.remote.RetrofitInstance
import retrofit2.Response

class PostRepository {
    suspend fun getPosts(): Response<PostItem> = RetrofitInstance.api.getPosts()
}