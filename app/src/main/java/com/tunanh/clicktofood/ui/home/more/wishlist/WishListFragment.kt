package com.tunanh.clicktofood.ui.home.more.wishlist

import com.tunanh.clicktofood.R
import com.tunanh.clicktofood.databinding.FragmentWishListBinding
import com.tunanh.clicktofood.ui.base.BaseFragment

class WishListFragment : BaseFragment<FragmentWishListBinding, WishListViewModel>() {
    override fun layoutRes(): Int = R.layout.fragment_wish_list
    override fun viewModelClass(): Class<WishListViewModel> = WishListViewModel::class.java

    override fun initView() {

    }


}