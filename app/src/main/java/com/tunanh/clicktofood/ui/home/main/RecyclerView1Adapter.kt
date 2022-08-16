package com.codefresher.tuananh.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView


class RecyclerView1Adapter:
    RecyclerView.Adapter<RecyclerView1Adapter.MyViewHolder>() {
    inner class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var title: TextView
        var img: ImageView

        init {
            title = itemView.findViewById(R.id.tvTitle)
            img = itemView.findViewById(R.id.imgItem)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val v =
            LayoutInflater.from(parent.context).inflate(R.layout.item_recyclerview1, parent, false)
        return MyViewHolder(v)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.title.text = listModel1[position].title
        holder.img.setImageResource(listModel1[position].img)
    }

    override fun getItemCount(): Int {
        return listModel1.size
    }
}