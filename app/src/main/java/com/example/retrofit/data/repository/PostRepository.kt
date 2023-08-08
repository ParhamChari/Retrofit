package com.example.retrofit.data.repository

import com.example.retrofit.data.model.Post
import com.example.retrofit.data.model.PostItem
import com.example.retrofit.data.remote.RetrofitInstance
import retrofit2.Response

class PostRepository {
    suspend fun getPosts(): Response<PostItem> = RetrofitInstance.api.getPosts()

    suspend fun getPostsNumber(number: Int): Response<PostItem> =
        RetrofitInstance.api.getPostsNumber(number)

    suspend fun getCustomPosts(userId: Int, sort: String, order: String): Response<Post> =
        RetrofitInstance.api.getCustomPost(userId, sort, order)
}