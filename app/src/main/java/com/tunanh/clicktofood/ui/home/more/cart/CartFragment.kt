package com.tunanh.clicktofood.ui.home.more.cart

import com.tunanh.clicktofood.R
import com.tunanh.clicktofood.data.local.model.Food
import com.tunanh.clicktofood.databinding.FragmentCartBinding
import com.tunanh.clicktofood.ui.base.BaseFragment

class CartFragment : BaseFragment<FragmentCartBinding,CartViewModel>() {
    override fun layoutRes(): Int =R.layout.fragment_cart
    val adapter=CartAdapter()

    override fun viewModelClass(): Class<CartViewModel> =CartViewModel::class.java

    override fun initView() {
        initRecycleView()
    }

    private fun initRecycleView() {
        viewModel.cart.observe(this) {
            adapter.itemFood = it as ArrayList<Food>
            binding.foodList.adapter = adapter
        }
        adapter.onClickPlus={
            adapter.notifyItemChanged(it)
        }
        adapter.onClickMinus={food, i ->
            if (food.amount==0){
                adapter.itemFood?.removeAt(i)
                adapter.notifyDataSetChanged()
            }else{
                adapter.notifyItemChanged(i)
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        viewModel.updateCart(adapter.itemFood as List<Food>)
    }
}