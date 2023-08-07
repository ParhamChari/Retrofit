package com.example.retrofit.ui.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.retrofit.data.model.PostItem
import com.example.retrofit.databinding.PostItemBinding

class ItemCardAdapter : RecyclerView.Adapter<ItemCardViewHolder>() {
    var postList = ArrayList<PostItem>()

    @SuppressLint("NotifyDataSetChanged")
    fun setData(model : List<PostItem>){
        this.postList = model as ArrayList<PostItem>
        notifyDataSetChanged()
    }

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

}