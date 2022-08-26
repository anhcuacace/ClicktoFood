package com.tunanh.clicktofood.ui.detail

import android.widget.Toast
import com.tunanh.clicktofood.R
import com.tunanh.clicktofood.data.local.model.Food
import com.tunanh.clicktofood.data.remote.model.Meal
import com.tunanh.clicktofood.databinding.FragmentDetailBinding
import com.tunanh.clicktofood.ui.base.BaseFragment
import com.tunanh.clicktofood.ui.custemview.BottomSheetDialogFragment
import com.tunanh.clicktofood.ui.main.MainActivity
import com.tunanh.clicktofood.ui.temp.TempAdapter
import com.tunanh.clicktofood.util.convertData
import com.tunanh.clicktofood.util.shareLink

class DetailFragment : BaseFragment<FragmentDetailBinding, DetailViewModel>() {
    override fun layoutRes(): Int = R.layout.fragment_detail
    override fun viewModelClass(): Class<DetailViewModel> = DetailViewModel::class.java
    private var food: Food? = null
    private val adapter = TempAdapter()
    override fun initView() {
        food = arguments?.get("food") as Food
        click()
        bindingData()
        recyclerview()
    }

    private fun recyclerview() {
        viewModel.foodList.observe(this){
            val data = convertData(it.meals as ArrayList<Meal>)
            adapter.foodList = data
            binding.foodList.adapter = adapter
            (activity as MainActivity).hiddenLoading()
        }
        adapter.onClickItem = { it ->
            val bottomSheetDialogFragment= BottomSheetDialogFragment(food = it)
            bottomSheetDialogFragment.show(childFragmentManager,"ActionButton")
        }
        adapter.onClickAdd={
            (activity as MainActivity).viewModel.addToCard(it)
            Toast.makeText(requireContext(), requireContext().getString(R.string.addfood), Toast.LENGTH_SHORT).show()
        }
    }

    private fun bindingData() {
        binding.star.text = "   ${food?.star.toString()}"
        binding.foodName.text = food?.title
        binding.foodName1.text = "${food?.title}, ngon tuyệt zời"
    }

    private fun click() {
        binding.actionBar.setOnClickImageLeft {
            getNavController().popBackStack()
        }
        binding.actionBar.setOnClickImageRight {
            food?.img?.let { shareLink(it, requireContext()) }
        }
        binding.actionBar.setOnClickImageRight2 {
            getNavController().navigate(R.id.action_detailFragment_to_searchFragment)
        }
    }


}