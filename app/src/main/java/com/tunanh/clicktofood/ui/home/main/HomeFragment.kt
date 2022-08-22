package com.tunanh.clicktofood.ui.home.main

import android.os.Handler
import android.os.Looper
import androidx.core.os.bundleOf
import androidx.navigation.fragment.findNavController
import androidx.viewpager2.widget.ViewPager2
import com.tunanh.clicktofood.R
import com.tunanh.clicktofood.data.remote.model.Meal
import com.tunanh.clicktofood.databinding.FragmentHomeBinding
import com.tunanh.clicktofood.ui.base.BaseFragment
import com.tunanh.clicktofood.ui.main.MainActivity
import com.tunanh.clicktofood.util.convertData

class HomeFragment : BaseFragment<FragmentHomeBinding, HomeViewModel>() {
    private var handler = Handler(Looper.getMainLooper())
    private val adapterViewPager2 = ViewPagerAdapter()
    override fun layoutRes(): Int = R.layout.fragment_home

    override fun viewModelClass(): Class<HomeViewModel> = HomeViewModel::class.java

    override fun initView() {
        (activity as MainActivity).showLoading()
        sliders()
        categories()
        foodList()
    }

    private fun foodList() {
        val recyclerViewAdapter = RecyclerViewAdapter()
        val recommendAdapter = RecommendAdapter()
        viewModel.foodList.observe(this) {
            val listMeals = it.meals as ArrayList<Meal>
//            val listMeals2= listMeals.sortBy { listMeal->listMeal.id } as ArrayList<Meals>
            val data = convertData(listMeals)
            recommendAdapter.foodList = data
            recyclerViewAdapter.foodList = data
            binding.recyclerView2.adapter = recyclerViewAdapter
            binding.recyclerViewRecommend.adapter = recommendAdapter
        }
    }

    private fun sliders() {


        viewModel.sliderList.observe(this) {
            adapterViewPager2.sliderList = it
            (activity as MainActivity).hiddenLoading()
            binding.viewPager2.adapter = adapterViewPager2
            binding.indicator.setViewPager(view?.findViewById(R.id.viewPager2))
            binding.viewPager2.registerOnPageChangeCallback(object :
                ViewPager2.OnPageChangeCallback() {
                override fun onPageSelected(position: Int) {
                    super.onPageSelected(position)
//                    handler.removeCallbacks(runnable())
                    if (position == 4) {

                        handler.postDelayed({
                            binding.viewPager2.currentItem = 0
                        }, 3000)
                    } else {
                        handler.postDelayed({
                            binding.viewPager2.currentItem = binding.viewPager2.currentItem + 1
                        }, 3000)
                    }

                }

            })
        }


    }

    private fun categories() {
        val adapter = CategoryHomeAdapter()

        viewModel.categoryList.observe(this) {
            adapter.categoryList = it.Categories
            binding.categoryes.adapter = adapter
        }
        adapter.onClickItem = { category, _ ->
            findNavController().navigate(
                R.id.action_mainFragment_to_tempFragment,
                bundleOf(
                    Pair("category", category.title)
                )
            )

        }
    }


    override fun onResume() {
        super.onResume()
        handler.postDelayed(
            { binding.viewPager2.currentItem = binding.viewPager2.currentItem + 1 },
            3000
        )
    }

}