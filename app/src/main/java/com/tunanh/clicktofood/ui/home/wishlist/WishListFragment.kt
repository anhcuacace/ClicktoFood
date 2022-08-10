package com.tunanh.clicktofood.ui.home.wishlist

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.tunanh.clicktofood.R
import com.tunanh.clicktofood.databinding.FragmentWishListBinding
import com.tunanh.clicktofood.ui.base.BaseFragment

class WishListFragment : BaseFragment<FragmentWishListBinding, WishListViewModel>() {
    override fun layoutRes(): Int =R.layout.fragment_wish_list
    override fun viewModelClass(): Class<WishListViewModel> =WishListViewModel::class.java

    override fun initView() {

    }
}