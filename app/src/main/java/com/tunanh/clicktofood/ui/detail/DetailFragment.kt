package com.tunanh.clicktofood.ui.detail

import android.os.Bundle
import androidx.navigation.fragment.findNavController
import com.tunanh.clicktofood.R
import com.tunanh.clicktofood.databinding.FragmentDetailBinding
import com.tunanh.clicktofood.ui.base.BaseFragment
import com.tunanh.clicktofood.ui.main.MainActivity
import com.tunanh.clicktofood.util.setOnSingClickListener

class DetailFragment : BaseFragment<FragmentDetailBinding, DetailViewModel>() {
    override fun layoutRes(): Int = R.layout.fragment_detail
    override fun viewModelClass(): Class<DetailViewModel> = DetailViewModel::class.java

    override fun initView() {
        click()
    }

    private fun click() {
        binding.back.setOnSingClickListener {
            (activity as MainActivity).onBackPressed()
        }
        binding.imgShare.setOnSingClickListener {
            getNavController().navigate(R.id.action_detailFragment_to_searchFragment)
        }
    }


}