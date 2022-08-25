package com.tunanh.clicktofood.ui.temp

import android.annotation.SuppressLint
import com.tunanh.clicktofood.R
import com.tunanh.clicktofood.data.remote.model.Meal
import com.tunanh.clicktofood.databinding.FragmentTempBinding
import com.tunanh.clicktofood.ui.base.BaseFragment
import com.tunanh.clicktofood.ui.custemview.BottomSheetDialogFragment
import com.tunanh.clicktofood.ui.main.MainActivity
import com.tunanh.clicktofood.util.convertData
import com.tunanh.clicktofood.util.setOnSingClickListener

class TempFragment : BaseFragment<FragmentTempBinding, TempViewModel>() {
    private val adapter = TempAdapter()
    override fun layoutRes(): Int = R.layout.fragment_temp

    override fun viewModelClass(): Class<TempViewModel> = TempViewModel::class.java

    override fun initView() {

        val categoryTitle = arguments?.getString("category").toString()
        categoryList(categoryTitle)
        click()

    }

    private fun click() {
        binding.back.setOnSingClickListener {
            (activity as MainActivity).onBackPressed()
        }

    }

    @SuppressLint("SetTextI18n")
    private fun categoryList(categoryTitle: String) {
        viewModel.callApi(categoryTitle)
        binding.listName.text = categoryTitle

        viewModel.foodList.observe(this) {
            val data = convertData(it.meals as ArrayList<Meal>)
            adapter.foodList = data
            binding.foodList.adapter = adapter
        }
        adapter.onClickItem = { it ->
            val bottomSheetDialogFragment=BottomSheetDialogFragment(food = it)
            bottomSheetDialogFragment.show(childFragmentManager,"ActionButton")
        }
    }


}