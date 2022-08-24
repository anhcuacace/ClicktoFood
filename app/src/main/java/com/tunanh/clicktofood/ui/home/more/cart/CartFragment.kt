package com.tunanh.clicktofood.ui.home.more.cart

import com.tunanh.clicktofood.R
import com.tunanh.clicktofood.databinding.FragmentCartBinding
import com.tunanh.clicktofood.ui.base.BaseFragment

class CartFragment : BaseFragment<FragmentCartBinding,CartViewModel>() {
    override fun layoutRes(): Int =R.layout.fragment_cart
    val adapter=CartAdapter()

    override fun viewModelClass(): Class<CartViewModel> =CartViewModel::class.java

    override fun initView() {

    }
}