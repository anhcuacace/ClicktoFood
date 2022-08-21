package com.tunanh.clicktofood.ui.temp

import android.annotation.SuppressLint
import com.bumptech.glide.Glide
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.tunanh.clicktofood.R
import com.tunanh.clicktofood.data.local.model.Food
import com.tunanh.clicktofood.data.remote.model.Meal
import com.tunanh.clicktofood.databinding.FragmentTempBinding
import com.tunanh.clicktofood.databinding.ItemModalBinding
import com.tunanh.clicktofood.ui.base.BaseFragment
import com.tunanh.clicktofood.util.convertData
import com.tunanh.clicktofood.util.setOnSingClickListener
import com.tunanh.clicktofood.util.shareLink

class TempFragment : BaseFragment<FragmentTempBinding, TempViewModel>() {
    private val adapter = TempAdapter()
    override fun layoutRes(): Int = R.layout.fragment_temp

    override fun viewModelClass(): Class<TempViewModel> = TempViewModel::class.java

    override fun initView() {

        val categoryTitle = arguments?.getString("category").toString()
        categoryList(categoryTitle)

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
            val v = ItemModalBinding.inflate(layoutInflater)
            val view = v.root
            val food = it
            val dialog = BottomSheetDialog(requireContext())
            Glide.with(requireContext()).load(food.img).error(R.mipmap.ic_launcher).into(v.imgFood)
            v.tvNameItem.text = food.title
            v.tvPrice.text = "${food.cost} $"
            v.lnShare.setOnSingClickListener { shareLink(food.img.toString(), requireContext()) }
            v.addToCard.setOnSingClickListener {
                viewModel.addToCard(food)
                dialog.dismiss()
            }

            dialog.setCancelable(true)
            dialog.setContentView(view)
            dialog.show()
        }
    }



}