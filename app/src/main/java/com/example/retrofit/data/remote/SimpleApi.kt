package com.example.retrofit.data.remote

import com.example.retrofit.data.model.Post
import com.example.retrofit.data.model.PostItem
import com.example.retrofit.utils.Constance
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface SimpleApi {

    @GET(Constance.END_POINT)
    suspend fun getPosts(): Response<PostItem>


    @GET(Constance.END_POINT)
    suspend fun getCustomPost(
        @Query("userId") userId : Int,
        @Query("_sort") sort : String,
        @Query("_order") order : String
    ) : Response<Post>

    @POST(Constance.END_POINT)
    suspend fun pushPosts(@Body postitem : PostItem) : Response<PostItem>
}