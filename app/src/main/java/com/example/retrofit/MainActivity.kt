package com.example.retrofit

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.example.retrofit.data.repository.PostRepository
import com.example.retrofit.databinding.ActivityMainBinding
import com.example.retrofit.ui.viewmodel.PostViewModel
import com.example.retrofit.ui.viewmodel.factory.PostViewModelFactory

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var viewModel: PostViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        bindViews()
    }

    private fun bindViews() {
        val repository = PostRepository()
        val viewModelFactory = PostViewModelFactory(repository)
        viewModel = ViewModelProvider(this, viewModelFactory)[PostViewModel::class.java]

        binding.Searchbtn.setOnClickListener {
            val number = binding.editText.text.toString().toInt()
            viewModel.getPostsNumber(number)
            viewModel.postNumberResponse.observe(this) { response->
                if (response.isSuccessful) {
                    binding.showId.text = "id: ${response.body()!!.id.toString()}"
                    binding.showTitle.text = "title: ${response.body()!!.title.toString()}"
                    binding.showBody.text = "body: ${response.body()!!.body.toString()}"
                } else {
                    Toast.makeText(this, response.code().toString(), Toast.LENGTH_LONG).show()
                }
            }
        }

    }
}