package com.tunanh.clicktofood.ui.home

import androidx.navigation.NavController
import com.tunanh.clicktofood.R
import com.tunanh.clicktofood.databinding.FragmentMainBinding
import com.tunanh.clicktofood.ui.base.BaseFragment
import com.tunanh.clicktofood.ui.home.category.CategoryFragment
import com.tunanh.clicktofood.ui.home.main.HomeFragment
import com.tunanh.clicktofood.ui.home.more.MoreFragment
import com.tunanh.clicktofood.ui.home.wishlist.WishListFragment


class MainFragment : BaseFragment<FragmentMainBinding, MainFragmentViewModel>() {

    override fun layoutRes(): Int = R.layout.fragment_main

    override fun viewModelClass(): Class<MainFragmentViewModel> = MainFragmentViewModel::class.java

    override fun initView() {
        val fragmentFirst = HomeFragment()
        replaceFragment(fragmentFirst)
        binding.buttonNavigation.setOnItemSelectedListener {


            when (it.itemId) {
                R.id.home2 -> {
                    val fragment = HomeFragment()
                    replaceFragment(fragment)
                    return@setOnItemSelectedListener true
                }
                R.id.category -> {
                    val fragment = CategoryFragment()
                    replaceFragment(fragment)
                    return@setOnItemSelectedListener true
                }
                R.id.wishList -> {
                    val fragment = WishListFragment()
                    replaceFragment(fragment)
                    return@setOnItemSelectedListener true
                }
                R.id.more -> {
                    val fragment = MoreFragment()
                    replaceFragment(fragment)
                    return@setOnItemSelectedListener true
                }

                else -> {
                    return@setOnItemSelectedListener false
                }
            }
        }
    }

    private fun replaceFragment(fragment: BaseFragment<*, *>) {
        val fragmentManager = childFragmentManager
        val fragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.frame_layout, fragment)
        fragmentTransaction.commit()
    }

}