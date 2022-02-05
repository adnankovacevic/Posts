package com.sample.jsonplaceholder.repository

import com.sample.jsonplaceholder.network.JsonPlaceholderApi
import javax.inject.Inject

class PostRepository @Inject constructor(private val api:JsonPlaceholderApi) {

    suspend fun getPosts() = api.getPosts()
}