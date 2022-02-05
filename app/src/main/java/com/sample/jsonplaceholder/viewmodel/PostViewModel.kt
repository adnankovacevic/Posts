package com.sample.jsonplaceholder.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sample.jsonplaceholder.model.Post
import com.sample.jsonplaceholder.repository.PostRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PostViewModel @Inject constructor(private val repository: PostRepository) : ViewModel() {
    private val _posts = MutableLiveData<List<Post>>()
    val posts: LiveData<List<Post>>
        get() = _posts

    init {
        getPostsResponse()
    }

    private fun getPostsResponse() = viewModelScope.launch {
        repository.getPosts().let { response ->
            if (response.isSuccessful) {
                _posts.postValue(response.body())
            } else {
                Log.d("ViewModel", "Error: ${response.errorBody()}")
            }
        }
    }
}