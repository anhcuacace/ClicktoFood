package com.tunanh.clicktofood.ui.home.cart

import com.tunanh.clicktofood.R
import com.tunanh.clicktofood.data.local.model.Food
import com.tunanh.clicktofood.databinding.FragmentCartBinding
import com.tunanh.clicktofood.ui.base.BaseFragment
import com.tunanh.clicktofood.ui.main.MainActivity

class CartFragment : BaseFragment<FragmentCartBinding, CartViewModel>() {
    override fun layoutRes(): Int = R.layout.fragment_cart
    private val adapter = CartAdapter()
    private var voucher: Double = 1.0
    private var fee: Int = 0


    override fun viewModelClass(): Class<CartViewModel> = CartViewModel::class.java

    override fun initView() {
        initRecycleView()
    }

    private fun initRecycleView() {
        (activity as MainActivity).viewModel.isLoadCart.observe(this) {
            if (it) {
                viewModel.addToCart()
                (activity as MainActivity).viewModel.isLoadCart.value = false
            }
        }
        viewModel.cart.observe(viewLifecycleOwner) {
            adapter.itemFood = it as ArrayList<Food>
            binding.foodList.adapter = adapter
            bill(adapter.itemFood as List<Food>)
        }
        adapter.onClickPlus = {
            adapter.notifyItemChanged(it)
            bill(adapter.itemFood as List<Food>)
        }
        adapter.onClickMinus = { food, i ->
            if (food.amount == 0) {
                adapter.itemFood?.removeAt(i)
                viewModel.deleteCart(food.id)
                adapter.notifyDataSetChanged()

            } else {
                adapter.notifyItemChanged(i)
            }
            bill(adapter.itemFood as List<Food>)
        }
    }

    private fun bill(listFood: List<Food>) {
        var sum = 0.0
        for (i in listFood.indices) {
            sum += listFood[i].amount * listFood[i].cost
        }
        sum *= voucher
        sum += fee
        binding.total.text = sum.toString()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        val listFood = adapter.itemFood as List<Food>
        viewModel.updateCart(listFood)
    }
}