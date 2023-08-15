package com.example.retrofit.data.remote

import com.example.retrofit.data.model.Post
import com.example.retrofit.data.model.PostItem
import com.example.retrofit.utils.Constants
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface SimpleApi {

    @GET(Constants.END_POINT)
    suspend fun getPosts(): Response<PostItem>


    @GET(Constants.END_POINT)
    suspend fun getCustomPost(
        @Query("userId") userId : Int,
        @Query("_sort") sort : String,
        @Query("_order") order : String
    ) : Response<Post>
}