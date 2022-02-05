package com.sample.jsonplaceholder

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.sample.jsonplaceholder.adapter.PostsAdapter
import com.sample.jsonplaceholder.databinding.ActivityMainBinding
import com.sample.jsonplaceholder.viewmodel.PostViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var postAdapter: PostsAdapter
    private val viewmodel: PostViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        postAdapter = PostsAdapter()

        rvSetup()
        observeState()
    }

    private fun rvSetup() {
        binding.postsRV.apply {
            adapter = postAdapter
            layoutManager = LinearLayoutManager(this@MainActivity)
            setHasFixedSize(true)
        }
    }

    private fun observeState() {
        viewmodel.posts.observe(this, { posts ->
            if (posts != null) {
                postAdapter.submitList(posts)
            }
        })
    }
}