package com.example.retrofit.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView.Adapter
import com.example.retrofit.data.model.PostItem
import com.example.retrofit.databinding.PostItemBinding

class ItemCardAdapter : Adapter<ItemCardViewHolder>() {
    var postList = emptyList<PostItem>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemCardViewHolder =
        ItemCardViewHolder(
            PostItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )


    override fun onBindViewHolder(holder: ItemCardViewHolder, position: Int) {
        holder.bindViews(postList[position])
    }

    override fun getItemCount(): Int {
        return postList.size
}

    fun setData(newList : List<PostItem>) {
        postList = newList
    }
}