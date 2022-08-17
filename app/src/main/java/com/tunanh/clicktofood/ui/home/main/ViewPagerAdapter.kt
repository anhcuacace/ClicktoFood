package com.tunanh.clicktofood.ui.home.main

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.core.content.ContentProviderCompat.requireContext
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.tunanh.clicktofood.R
import com.tunanh.clicktofood.data.remote.model.Slider
import com.tunanh.clicktofood.databinding.ItemRclvMoreBinding
import com.tunanh.clicktofood.databinding.ItemSlideBinding
import com.tunanh.clicktofood.ui.home.more.ItemMore

class ViewPagerAdapter : RecyclerView.Adapter<ViewPagerAdapter.PagerViewHolder>(){
    inner class PagerViewHolder constructor(private val binding:ItemSlideBinding): RecyclerView.ViewHolder(binding.root){
        fun bind(slider: Slider) {
            Glide.with(itemView.context).load(slider.img).error(R.mipmap.ic_launcher).into(binding.imgSlide)

        }
    }
    var sliderList:List<Slider>?=null
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PagerViewHolder {
        val binding =
            ItemSlideBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return PagerViewHolder(binding)
    }

    override fun onBindViewHolder(holder: PagerViewHolder, position: Int) {
        sliderList?.get(position)?.let { holder.bind(it) }
    }

    override fun getItemCount(): Int {
      return sliderList?.size ?:0
    }
}