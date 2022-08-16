package com.tunanh.clicktofood.ui.home.main

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.tunanh.clicktofood.R

class ViewPagerAdapter (private var images: List<Int>): RecyclerView.Adapter<ViewPagerAdapter.PagerViewHolder>(){
    inner class PagerViewHolder(itemView:View): RecyclerView.ViewHolder(itemView){
            val img: ImageView = itemView.findViewById(R.id.imgSlide)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PagerViewHolder {
      return PagerViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_slide,parent,false))
    }

    override fun onBindViewHolder(holder: PagerViewHolder, position: Int) {
       holder.img.setImageResource(images[position])
    }

    override fun getItemCount(): Int {
      return images.size
    }
}