package com.example.retrofit

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.retrofit.data.model.PostItem
import com.example.retrofit.data.repository.PostRepository
import com.example.retrofit.databinding.ActivityMainBinding
import com.example.retrofit.ui.adapter.ItemCardAdapter
import com.example.retrofit.ui.viewmodel.PostViewModel
import com.example.retrofit.ui.viewmodel.factory.PostViewModelFactory

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var viewModel: PostViewModel
    private val postAdapter by lazy { ItemCardAdapter() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        bindViews()
    }

    private fun bindViews() {
        // initializing recycler view
        /* binding.recyclerview.apply {
            layoutManager = LinearLayoutManager(this@MainActivity, LinearLayoutManager.VERTICAL, false)
            adapter = postAdapter
        } */

        val repository = PostRepository()
        val viewModelFactory = PostViewModelFactory(repository)
        viewModel = ViewModelProvider(this, viewModelFactory)[PostViewModel::class.java]

        val postItem = PostItem(10,100,"Retrofit","Retrofit Post App")
        viewModel.setPosts(postItem)
        viewModel.pushPostsResponse.observe(this) { response->
            if (response.isSuccessful) {
                Toast.makeText(this, response.body().toString(), Toast.LENGTH_LONG).show()
            } else Toast.makeText(this, response.code().toString(), Toast.LENGTH_LONG).show()
        }

    }


}