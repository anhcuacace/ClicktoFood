package com.tunanh.clicktofood.ui.home.more.cart

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.tunanh.clicktofood.R
import com.tunanh.clicktofood.data.local.model.Food
import com.tunanh.clicktofood.databinding.ItemCartBinding
import com.tunanh.clicktofood.databinding.ItemRclvMoreBinding
import com.tunanh.clicktofood.databinding.ItemSlideBinding
import com.tunanh.clicktofood.ui.home.more.ItemMore
import com.tunanh.clicktofood.util.setOnSingClickListener

class CartAdapter :RecyclerView.Adapter<CartAdapter.CartViewHolder>() {
    inner class CartViewHolder constructor(private val binding: ItemCartBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(food: Food) {
            Glide.with(itemView.context).load(food.img).error(R.mipmap.ic_launcher).into(binding.imgFood)
            binding.tvTitleItem.text=food.title
            binding.cost.text=food.cost.toString()
            binding.count.text=food.amount.toString()
            binding.tvRateCount.text=food.star.toString()
        }
    }

    var itemMore: List<Food>? = null
    var onClickItem: ((Food, Int) -> Unit)? = null
    var
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CartViewHolder {
        val binding =
            ItemCartBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return CartViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CartViewHolder, position: Int) {
        itemMore?.let { holder.bind(it[position]) }
        holder.itemView.setOnSingClickListener {
            itemMore?.let { it1 -> onClickItem?.invoke(it1[position],position) }
        }
    }

    override fun getItemCount(): Int {
        return itemMore?.size ?: 0
    }
}