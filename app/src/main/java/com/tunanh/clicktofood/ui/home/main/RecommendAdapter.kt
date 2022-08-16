package com.codefresher.tuananh.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView


class RecommendAdapter :
    RecyclerView.Adapter<RecommendAdapter.MyViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val v =
            LayoutInflater.from(parent.context).inflate(R.layout.item_recommend, parent, false)
        return MyViewHolder(v)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {

        holder.img.setImageResource(listRecommend[position].img)
        holder.rateCount.text = listRecommend[position].rateCount
        holder.nameAndAddress.text = listRecommend[position].nameAndAddress
        holder.hashTag.text = listRecommend[position].hastTag
        holder.distance.text = listRecommend[position].distance
    }

    override fun getItemCount(): Int {
        return listRecommend.size
    }
}