package com.tunanh.clicktofood.ui.detail

import com.tunanh.clicktofood.R
import com.tunanh.clicktofood.databinding.FragmentDetailBinding
import com.tunanh.clicktofood.ui.base.BaseFragment

class DetailFragment : BaseFragment<FragmentDetailBinding, DetailViewModel>() {
    override fun layoutRes(): Int =R.layout.fragment_detail
    override fun viewModelClass(): Class<DetailViewModel> =DetailViewModel::class.java

    override fun initView() {

    }


}