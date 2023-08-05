package com.example.retrofit.ui.adapter

import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.example.retrofit.data.model.PostItem
import com.example.retrofit.databinding.PostItemBinding
import com.example.retrofit.ui.viewmodel.PostViewModel

class ItemCardViewHolder(private val binding: PostItemBinding) : RecyclerView.ViewHolder(binding.root) {

    fun bindViews(model: PostItem) {

        binding.apply {
            title.text = model.title
        }
    }
}