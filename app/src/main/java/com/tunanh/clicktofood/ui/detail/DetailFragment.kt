package com.tunanh.clicktofood.ui.detail

import com.tunanh.clicktofood.R
import com.tunanh.clicktofood.data.local.model.Food
import com.tunanh.clicktofood.databinding.FragmentDetailBinding
import com.tunanh.clicktofood.ui.base.BaseFragment
import com.tunanh.clicktofood.util.shareLink

class DetailFragment : BaseFragment<FragmentDetailBinding, DetailViewModel>() {
    override fun layoutRes(): Int = R.layout.fragment_detail
    override fun viewModelClass(): Class<DetailViewModel> = DetailViewModel::class.java
    private var food:Food?=null
    override fun initView() {
        food= arguments?.get("food") as Food
        click()
        bindingData()
    }

    private fun bindingData() {
        binding.tvStar.text= food?.star.toString()
        binding.foodName.text=food?.title
        binding.foodName1.text="${food?.title}, ngon tuyệt zời"
    }

    private fun click() {
        binding.actionBar.setOnClickImageLeft {
            getNavController().popBackStack()
        }
        binding.actionBar.setOnClickImageRight {
            food?.img?.let { shareLink(it,requireContext()) }
        }
        binding.actionBar.setOnClickImageRight2 {
            getNavController().navigate(R.id.action_detailFragment_to_searchFragment)
        }
    }


}