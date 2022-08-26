package com.tunanh.clicktofood.ui.temp

import android.widget.Toast
import com.tunanh.clicktofood.R
import com.tunanh.clicktofood.data.remote.model.Meal
import com.tunanh.clicktofood.databinding.FragmentTempBinding
import com.tunanh.clicktofood.ui.base.BaseFragment
import com.tunanh.clicktofood.ui.custemview.BottomSheetDialogFragment
import com.tunanh.clicktofood.ui.main.MainActivity
import com.tunanh.clicktofood.util.convertData

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
        binding.actionBar.setOnClickImageLeft {
            getNavController().popBackStack()
        }

    }


    private fun categoryList(categoryTitle: String) {
        viewModel.callApi(categoryTitle)
        (activity as MainActivity).showLoading()
        binding.actionBar.setTitle(categoryTitle)

        viewModel.foodList.observe(this) {
            val data = convertData(it.meals as ArrayList<Meal>)
            adapter.foodList = data
            binding.foodList.adapter = adapter
            (activity as MainActivity).hiddenLoading()
        }
        adapter.onClickItem = { it ->
            val bottomSheetDialogFragment=BottomSheetDialogFragment(food = it)
            bottomSheetDialogFragment.show(childFragmentManager,"ActionButton")
        }
        adapter.onClickAdd={
            (activity as MainActivity).viewModel.addToCard(it)
            Toast.makeText(requireContext(), requireContext().getString(R.string.addfood), Toast.LENGTH_SHORT).show()
        }
    }


}