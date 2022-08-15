package com.tunanh.clicktofood.ui.home.more

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.tunanh.clicktofood.databinding.ItemRclvMoreBinding
import com.tunanh.clicktofood.util.setOnSingClickListener

class AccountAdapter : RecyclerView.Adapter<AccountAdapter.AccountViewHolder>() {
    inner class AccountViewHolder constructor(private val binding: ItemRclvMoreBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(itemMore: ItemMore) {
            binding.imageItem.setImageResource(itemMore.img)
            binding.text.text = itemMore.text
        }
    }

    var itemMore: List<ItemMore>? = null
    var onClickItem: ((ItemMore, Int) -> Unit)? = null
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AccountViewHolder {
        val binding =
            ItemRclvMoreBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return AccountViewHolder(binding)
    }

    override fun onBindViewHolder(holder: AccountViewHolder, position: Int) {
        itemMore?.let { holder.bind(it[position]) }
        holder.itemView.setOnSingClickListener {
            itemMore?.get(position)
                .let { it1 -> it1?.let { it2 -> onClickItem?.invoke(it2, position) } }
        }
    }

    override fun getItemCount(): Int {
        return itemMore?.size ?: 0
    }
}