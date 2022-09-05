package com.tunanh.clicktofood.ui.detail

import android.widget.Toast
import com.tunanh.clicktofood.R
import com.tunanh.clicktofood.data.local.model.Food
import com.tunanh.clicktofood.databinding.FragmentDetailBinding
import com.tunanh.clicktofood.ui.base.BaseFragment
import com.tunanh.clicktofood.ui.custemview.BottomSheetDialogFragment
import com.tunanh.clicktofood.ui.main.MainActivity
import com.tunanh.clicktofood.ui.search.SearchFoodAdapter
import com.tunanh.clicktofood.util.shareLink

class DetailFragment : BaseFragment<FragmentDetailBinding, DetailViewModel>() {
    override fun layoutRes(): Int = R.layout.fragment_detail
    override fun viewModelClass(): Class<DetailViewModel> = DetailViewModel::class.java
    private var food: Food? = null
    private val adapter = SearchFoodAdapter()
    override fun initView() {
        food = arguments?.get("food") as Food
        click()
        bindingData()
        recyclerview()
    }

    private fun recyclerview() {
        binding.foodList.adapter = adapter
        viewModel.foodList.observe(this){
            adapter.addFoodList(it)
        }
        adapter.onClickItem = { it ->
            val food=Food(it.id,it.title,it.cost, star = it.star, img = it.img)
            val bottomSheetDialogFragment= BottomSheetDialogFragment(food = food)
            bottomSheetDialogFragment.show(childFragmentManager,"ActionButton")
        }
        adapter.onClickAdd={
            val food=Food(it.id,it.title,it.cost, star = it.star, img = it.img)
            (activity as MainActivity).viewModel.addToCard(food.also { food.amount+=1 })
            Toast.makeText(requireContext(), requireContext().getString(R.string.addfood), Toast.LENGTH_SHORT).show()
        }
        adapter.onClickLike={
            (activity as MainActivity).viewModel.updateLove(it)
            Toast.makeText(requireContext(), "added to favorites", Toast.LENGTH_SHORT).show()
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