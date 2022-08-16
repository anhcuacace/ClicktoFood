package com.codefresher.tuananh.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView


class RecyclerView2Adapter(private val listModel2: ArrayList<model2>) :
    RecyclerView.Adapter<RecyclerView2Adapter.MyViewHolder>() {
    inner class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var tvRating: TextView
        var tvDT: TextView
        var title: TextView
        var img: ImageView

        init {
            title = itemView.findViewById(R.id.nameItem)
            tvRating = itemView.findViewById(R.id.tvRating)
            tvDT = itemView.findViewById(R.id.tvDistanceAndTime)
            img = itemView.findViewById(R.id.imgItem2)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val v =
            LayoutInflater.from(parent.context).inflate(R.layout.item_recyclerview2, parent, false)
        return MyViewHolder(v)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.title.text = listModel2[position].title
        holder.tvRating.text = listModel2[position].rating
        holder.tvDT.text = listModel2[position].distanceTime
        holder.img.setImageResource(listModel2[position].img)

    }

    override fun getItemCount(): Int {
        return listModel2.size
    }
}