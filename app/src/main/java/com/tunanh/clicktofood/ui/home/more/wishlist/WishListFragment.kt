package com.tunanh.clicktofood.ui.home.more.wishlist

import android.view.View
import android.widget.Toast
import com.tunanh.clicktofood.R
import com.tunanh.clicktofood.data.local.model.Food
import com.tunanh.clicktofood.databinding.FragmentWishListBinding
import com.tunanh.clicktofood.ui.base.BaseFragment
import com.tunanh.clicktofood.ui.custemview.BottomSheetDialogFragment
import com.tunanh.clicktofood.ui.main.MainActivity
import com.tunanh.clicktofood.ui.temp.TempAdapter

class WishListFragment : BaseFragment<FragmentWishListBinding, WishListViewModel>() {
    override fun layoutRes(): Int = R.layout.fragment_wish_list
    override fun viewModelClass(): Class<WishListViewModel> = WishListViewModel::class.java

    override fun initView() {
        wishList()
        actionBar()
    }

    private fun actionBar() {
        binding.actionBar.setOnClickImageLeft {
            getNavController().popBackStack()
        }
    }

    private fun wishList() {
        val adapter = TempAdapter()
        viewModel.withList.observe(viewLifecycleOwner) {
            if (it == null || it.isEmpty()) {
                binding.tvEmpty.visibility = View.VISIBLE
            } else {
                adapter.foodList = it as ArrayList<Food>
                binding.list.adapter = adapter
            }

        }
        adapter.onClickLike = { food, i ->
            mainViewModel.updateLove(food)
            Toast.makeText(requireContext(), "đã xóa ${food.title}", Toast.LENGTH_SHORT).show()
            adapter.foodList?.removeAt(i)
            adapter.notifyDataSetChanged()
            if (adapter.foodList?.isEmpty() == true) {
                binding.tvEmpty.visibility = View.VISIBLE
            }
        }
        adapter.onClickItem = { it ->
            val bottomSheetDialogFragment = BottomSheetDialogFragment(food = it)
            bottomSheetDialogFragment.show(childFragmentManager, "ActionButton")
        }
        adapter.onClickAdd = { food ->
            mainViewModel.addToCard(food.also { food.amount += 1 })
            Toast.makeText(
                requireContext(),
                requireContext().getString(R.string.addfood),
                Toast.LENGTH_SHORT
            ).show()
        }

    }


}