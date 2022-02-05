package com.sample.jsonplaceholder.network

import com.sample.jsonplaceholder.model.Post
import retrofit2.Response
import retrofit2.http.GET

interface JsonPlaceholderApi {

    @GET("posts")
    suspend fun getPosts(): Response<List<Post>>
}