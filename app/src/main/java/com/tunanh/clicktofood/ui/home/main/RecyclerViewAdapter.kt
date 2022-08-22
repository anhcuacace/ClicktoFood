package com.tunanh.clicktofood.ui.home.main

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.tunanh.clicktofood.R
import com.tunanh.clicktofood.data.local.model.Food
import com.tunanh.clicktofood.databinding.ItemRecyclerview2Binding


class RecyclerViewAdapter :
    RecyclerView.Adapter<RecyclerViewAdapter.MyViewHolder>() {
    inner class MyViewHolder constructor(private val binding: ItemRecyclerview2Binding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(food: Food) {
            Glide.with(itemView.context).load(food.img).error(R.mipmap.ic_launcher)
                .into(binding.imgItem2)
            binding.nameItem.text = food.title
            binding.tvRating.text = food.star.toString()
        }
    }

    var foodList: List<Food>? = null
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val v =
            ItemRecyclerview2Binding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MyViewHolder(v)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        foodList?.let { holder.bind(it[position]) }
    }

    override fun getItemCount(): Int = foodList?.size ?: 0
}