package com.tunanh.clicktofood.ui.temp

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.tunanh.clicktofood.R
import com.tunanh.clicktofood.data.local.model.Food
import com.tunanh.clicktofood.databinding.ItemTempBinding
import com.tunanh.clicktofood.util.setOnSingClickListener

class TempAdapter : RecyclerView.Adapter<TempAdapter.MyViewHolder>() {


    inner class MyViewHolder constructor( val binding: ItemTempBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(food: Food) {
            Glide.with(itemView.context).load(food.img).error(R.mipmap.ic_launcher)
                .into(binding.imgFood)
            binding.tvTitleItem.text = food.title
            binding.cost.text = "${food.cost} $"
        }
    }
    var onClickAdd:((Food) -> Unit)?=null
    var onClickItem: ((Food) -> Unit)? = null
    var foodList: List<Food>? = null
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val v =
            ItemTempBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MyViewHolder(v)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        foodList?.let { holder.bind(it[position]) }
        holder.itemView.setOnSingClickListener {
            foodList?.get(position)?.let { it1 -> onClickItem?.invoke(it1) }

        }
        holder.binding.tvAdd.setOnSingClickListener {
            foodList?.get(position)?.let { it1->onClickAdd?.invoke(it1) }
        }
    }

    override fun getItemCount(): Int = foodList?.size ?: 0

}