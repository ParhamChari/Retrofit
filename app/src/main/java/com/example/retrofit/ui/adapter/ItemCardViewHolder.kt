package com.example.retrofit.ui.adapter

import androidx.recyclerview.widget.RecyclerView
import com.example.retrofit.data.model.PostItem
import com.example.retrofit.databinding.PostItemBinding

class ItemCardViewHolder(private val binding: PostItemBinding) : RecyclerView.ViewHolder(binding.root) {

    fun bindViews(model: PostItem) {

        binding.apply {
            title.text = model.title
            description.text = model.body
            idNumber.text = model.id.toString()
        }

    }
}