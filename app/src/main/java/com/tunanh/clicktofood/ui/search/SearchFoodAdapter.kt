package com.tunanh.clicktofood.ui.search

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.tunanh.clicktofood.R
import com.tunanh.clicktofood.data.local.model.FoodData
import com.tunanh.clicktofood.databinding.ItemTempBinding
import com.tunanh.clicktofood.util.setOnSingClickListener

class SearchFoodAdapter : RecyclerView.Adapter<SearchFoodAdapter.FoodViewHolder>() {
    var onClickItem: ((FoodData)->Unit) ?= null
    private var foodList: List<FoodData>? = null
    var onClickAdd:((FoodData) -> Unit)?=null

    class FoodViewHolder constructor(val binding: ItemTempBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(foodData: FoodData) {
            Glide.with(itemView.context).load(foodData.img).error(R.mipmap.ic_launcher)
                .into(binding.imgFood)
            binding.tvTitleItem.text = foodData.title
            binding.cost.text = "${foodData.cost} $"
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FoodViewHolder {
        val v =
            ItemTempBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return FoodViewHolder(v)
    }

    override fun onBindViewHolder(holder: FoodViewHolder, position: Int) {
        holder.bind(foodList!![position])
        holder.itemView.setOnSingClickListener {
            onClickItem?.invoke(foodList!![position])
        }
        holder.binding.tvAdd.setOnSingClickListener {
            onClickAdd?.invoke(foodList!![position])

        }
    }

    override fun getItemCount(): Int = if (foodList != null) foodList?.size!! else 0

    @SuppressLint("NotifyDataSetChanged")
    fun addFoodList(foodList: List<FoodData>) {
        this.foodList = foodList
        notifyDataSetChanged()
    }
}