package com.tunanh.clicktofood.ui.home.main

import android.os.Handler
import android.os.Looper
import androidx.viewpager2.widget.ViewPager2
import com.tunanh.clicktofood.R
import com.tunanh.clicktofood.data.remote.model.Food
import com.tunanh.clicktofood.data.remote.model.Meal
import com.tunanh.clicktofood.databinding.FragmentHomeBinding
import com.tunanh.clicktofood.ui.base.BaseFragment
import com.tunanh.clicktofood.ui.main.MainActivity

class HomeFragment : BaseFragment<FragmentHomeBinding, HomeViewModel>() {
    override fun layoutRes(): Int = R.layout.fragment_home

    override fun viewModelClass(): Class<HomeViewModel> = HomeViewModel::class.java

    override fun initView() {
        (activity as MainActivity).showLoading()
        sliders()
        categories()
        foodList()
    }

    private fun foodList() {
        val recyclerViewAdapter=RecyclerViewAdapter()
        val recommendAdapter=RecommendAdapter()
        viewModel.foodList.observe(this){
            val listMeals= it.meals as ArrayList<Meal>
//            val listMeals2= listMeals.sortBy { listMeal->listMeal.id } as ArrayList<Meals>
            val data=convertData(listMeals)
            recommendAdapter.foodList=data
            recyclerViewAdapter.foodList=data
            binding.recyclerView2.adapter=recyclerViewAdapter
            binding.recyclerViewRecommend.adapter=recommendAdapter
        }
    }
    private fun convertData(array: ArrayList<Meal>):List<Food>{
        val data= ArrayList<Food>()
        for (i in 0..array.size ){
            val food=Food(id = array[i].id, title = array[i].title,cost = null, star = null, img = array[i].img)
            data.add(food)
        }
        return data as List<Food>
    }




    private fun sliders() {
        val adapter = ViewPagerAdapter()
        fun runnable() = Runnable {
            if (binding.viewPager2.currentItem == (adapter.sliderList?.size?.minus(1) ?: 0)) {
                binding.viewPager2.currentItem = 0
            } else {
                binding.viewPager2.currentItem = binding.viewPager2.currentItem + 1
            }

        }
        binding.viewPager2.adapter = adapter
        viewModel.sliderList.observe(this) {
            adapter.sliderList = it
            (activity as MainActivity).hiddenLoading()
        }
        binding.indicator.setViewPager(binding.viewPager2)
        binding.viewPager2.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
                Handler(Looper.getMainLooper()).removeCallbacks(runnable())

                Handler(Looper.getMainLooper()).postDelayed({
                    runnable()
                }, 3000)
            }

        })
    }

    private fun categories() {
        val adapter= CategoryHomeAdapter()
        binding.categoryes.adapter=adapter
        viewModel.categoryList.observe(this){
            adapter.categoryList= it.Categories
        }
//        adapter.onClickItem= {category, i ->
//            bundleOf(
//
//            )
//        }
    }

    override fun backPress(): Boolean = false

}