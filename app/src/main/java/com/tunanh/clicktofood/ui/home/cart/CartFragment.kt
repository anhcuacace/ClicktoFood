package com.tunanh.clicktofood.ui.home.cart

import android.view.View
import android.widget.Toast
import com.tunanh.clicktofood.R
import com.tunanh.clicktofood.data.local.model.Food
import com.tunanh.clicktofood.databinding.FragmentCartBinding
import com.tunanh.clicktofood.listener.OnClickConfirmDialog
import com.tunanh.clicktofood.ui.base.BaseFragment
import com.tunanh.clicktofood.ui.main.MainActivity
import com.tunanh.clicktofood.util.setOnSingClickListener

class CartFragment : BaseFragment<FragmentCartBinding, CartViewModel>() {
    override fun layoutRes(): Int = R.layout.fragment_cart
    private val adapter = CartAdapter()
    private var voucher: Double = 1.0
    private var fee: Int = 0
    private var array = ArrayList<Food>()

    override fun viewModelClass(): Class<CartViewModel> = CartViewModel::class.java

    override fun initView() {
        initRecycleView()
        placeOrder()
    }

    private fun placeOrder() {
        binding.placedOrder.setOnSingClickListener {
            showDialog("Thông báo",
                "Tổng tiền bạn là ${binding.total.text}\n vui lòng chờ ít phút",
                "Đồng ý",
                "Xác Nhận",
                object : OnClickConfirmDialog {
                    override fun onClickRightButton() {
                        viewModel.placeOrder(array)
                    }

                    override fun onClickLeftButton() {
                    }

                })


        }
        viewModel.loadDone = {
            getNavController().navigate(
                R.id.action_mainFragment_to_placedOrderFragment
            )
        }
    }

    private fun initRecycleView() {
        (activity as MainActivity).viewModel.isLoadCart = {
            viewModel.addToCart()
        }
        viewModel.cart.observe(viewLifecycleOwner) {
            array = it as ArrayList<Food>
            if (array.isEmpty()) {
                binding.foodList.visibility = View.GONE
                binding.tvEmpty.visibility = View.VISIBLE
            } else {
                binding.foodList.visibility = View.VISIBLE
                binding.tvEmpty.visibility = View.GONE
                adapter.itemFood = array
                binding.foodList.adapter = adapter
                bill(array)
            }

        }
        adapter.onClickPlus = { food, i ->
            adapter.notifyItemChanged(i)
            array = adapter.itemFood!!
            bill(array)
            (activity as MainActivity).viewModel.addToCard(food)
        }
        adapter.onClickMinus = { food, i ->
            if (food.amount == 0) {
                array.removeAt(i)
                adapter.itemFood = array
                viewModel.deleteCart(food.id)
                adapter.notifyDataSetChanged()
                binding.foodList.visibility = View.GONE
                binding.tvEmpty.visibility = View.VISIBLE
            } else {
                adapter.notifyItemChanged(i)
            }
            array = adapter.itemFood!!
            bill(array)
            (activity as MainActivity).viewModel.addToCard(food)
        }
    }

    private fun bill(listFood: ArrayList<Food>) {
        var sum = 0.0
        for (i in listFood.indices) {
            sum += listFood[i].amount * listFood[i].cost
        }
        sum *= voucher
        sum += fee
        binding.total.text = "$sum $"
        if (sum.toString().isEmpty()) {
            binding.constraintLayout.visibility = View.GONE
        } else {
            binding.constraintLayout.visibility = View.VISIBLE
        }
    }

    override fun networkFail() {

    }

    override fun successfulNetwork() {
    }


}