package com.tunanh.clicktofood.ui.placedorder

import com.tunanh.clicktofood.R
import com.tunanh.clicktofood.databinding.FragmentPlacedOrderBinding
import com.tunanh.clicktofood.ui.base.BaseFragment
import com.tunanh.clicktofood.util.setOnSingClickListener

class PlacedOrderFragment : BaseFragment<FragmentPlacedOrderBinding,PlacedOrderViewModel>() {
    override fun layoutRes(): Int =R.layout.fragment_placed_order

    override fun viewModelClass(): Class<PlacedOrderViewModel> =PlacedOrderViewModel::class.java

    override fun initView() {
        binding.goHome.setOnSingClickListener {
            getNavController().navigate(R.id.action_placedOrderFragment_to_mainFragment)
        }
    }


}