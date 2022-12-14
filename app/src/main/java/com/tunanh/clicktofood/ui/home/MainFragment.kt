package com.tunanh.clicktofood.ui.home
import androidx.fragment.app.Fragment
import com.tunanh.clicktofood.R
import com.tunanh.clicktofood.databinding.FragmentMainBinding
import com.tunanh.clicktofood.ui.base.BaseFragment
import com.tunanh.clicktofood.ui.home.cart.CartFragment
import com.tunanh.clicktofood.ui.home.category.CategoryFragment
import com.tunanh.clicktofood.ui.home.main.HomeFragment
import com.tunanh.clicktofood.ui.home.more.MoreFragment


class MainFragment : BaseFragment<FragmentMainBinding, MainFragmentViewModel>() {
    companion object {
        const val POSITION_HOME = 0
        const val POSITION_CATEGORY = 1
        const val POSITION_CART = 2
        const val POSITION_MORE = 3
    }

    private var listFragment = mutableListOf<Fragment>()
    private var currentPosition = 0
    private var isFragmentAdded = false
    override fun layoutRes(): Int = R.layout.fragment_main

    override fun viewModelClass(): Class<MainFragmentViewModel> = MainFragmentViewModel::class.java

    override fun initView() {
        listFragment.add(POSITION_HOME, HomeFragment())
        listFragment.add(POSITION_CATEGORY, CategoryFragment())
        listFragment.add(POSITION_CART, CartFragment())
        listFragment.add(POSITION_MORE, MoreFragment.newInstance())
        val fragmentTransaction = childFragmentManager.beginTransaction()
        listFragment.forEachIndexed { index, fragment ->
            if (!isFragmentAdded) {
                fragmentTransaction.add(R.id.frame_layout, fragment, fragment.javaClass.simpleName)
                if (index != 0) {
                    fragmentTransaction.hide(fragment)
                } else {
                    fragmentTransaction.show(fragment)
                }
            }
        }
        fragmentTransaction.commitAllowingStateLoss()
        isFragmentAdded = true
        binding.buttonNavigation.setOnItemSelectedListener { item ->
            when (item.itemId) {
                R.id.home -> {
                    openTab(POSITION_HOME)
                    return@setOnItemSelectedListener true
                }
                R.id.category -> {
                    openTab(POSITION_CATEGORY)
                    return@setOnItemSelectedListener true
                }
                R.id.cart -> {
                    openTab(POSITION_CART)
                    return@setOnItemSelectedListener true
                }
                R.id.more -> {
                    openTab(POSITION_MORE)
                    return@setOnItemSelectedListener true
                }
                else -> {
                    return@setOnItemSelectedListener false
                }
            }
        }
    }


    private fun openTab(position: Int) {
        val fragmentTransaction = childFragmentManager.beginTransaction()
        listFragment.forEachIndexed { index, fragment ->
            if (index == position) {
                fragmentTransaction.show(fragment)
            } else {
                fragmentTransaction.hide(fragment)
            }
        }
        if (position > currentPosition) {
            fragmentTransaction.setCustomAnimations(R.anim.trans_right_in, R.anim.trans_right_in)
        } else {
            fragmentTransaction.setCustomAnimations(R.anim.trans_right_out, R.anim.trans_right_out)
        }
        currentPosition = position
        fragmentTransaction.commitAllowingStateLoss()
    }


}