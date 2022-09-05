package com.tunanh.clicktofood.ui.home.main

import android.os.Handler
import android.os.Looper
import android.view.View
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.lifecycle.ViewModelProvider
import androidx.viewpager2.widget.ViewPager2
import com.tunanh.clicktofood.R
import com.tunanh.clicktofood.databinding.FragmentHomeBinding
import com.tunanh.clicktofood.ui.base.BaseFragment
import com.tunanh.clicktofood.ui.home.MainFragmentViewModel
import com.tunanh.clicktofood.ui.main.MainActivity
import com.tunanh.clicktofood.util.openWebsite
import com.tunanh.clicktofood.util.setOnSingClickListener

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
        onClick()
    }

    private fun onClick() {
        binding.seeAll.setOnSingClickListener {
            getNavController().navigate(
                R.id.action_mainFragment_to_tempFragment,
                bundleOf(Pair("category", "Beef"))
            )
        }
        binding.searchView.setOnSingClickListener {
            getNavController().navigate(
                R.id.action_mainFragment_to_searchFragment
            )
        }
    }

    private fun foodList() {
        val recyclerViewAdapter = RecyclerViewAdapter()
        val recommendAdapter = RecommendAdapter()
        viewModel.foodList.observe(this) {
            if (it == null || it.isEmpty()) {
                binding.rclv.visibility = View.GONE
            } else {
                recommendAdapter.foodList = it
                recyclerViewAdapter.foodList = it

                binding.recyclerView2.adapter = recyclerViewAdapter
                binding.recyclerViewRecommend.adapter = recommendAdapter
            }

        }
        recommendAdapter.onClickLike = { food ->
            (activity as MainActivity).viewModel.updateLove(food)
            Toast.makeText(requireContext(), "added to favorites", Toast.LENGTH_SHORT).show()
        }
        recommendAdapter.onClickItem = {
            getNavController().navigate(
                R.id.action_mainFragment_to_detailFragment,
                bundleOf(Pair("food", it))
            )
        }
        recyclerViewAdapter.onClickItem = {
            getNavController().navigate(
                R.id.action_mainFragment_to_detailFragment,
                bundleOf(Pair("food", it))
            )
        }
    }

    private fun sliders() {


        viewModel.sliderList.observe(this) {
            adapterViewPager2.sliderList = it
            (activity as MainActivity).hiddenLoading()
            binding.viewPager2.adapter = adapterViewPager2
            adapterViewPager2.onClickItem = { sliders ->
                sliders.img?.let { it1 -> openWebsite(it1, requireContext()) }
            }
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
        val myViewModel: MainFragmentViewModel =
            ViewModelProvider(this, viewModelFactory)[MainFragmentViewModel::class.java]
        myViewModel.categoryList.observe(this) {
            adapter.categoryList = it.Categories
            binding.categoryes.adapter = adapter
        }
        adapter.onClickItem = { category, _ ->
            getNavController().navigate(
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

    override fun networkFail() {

    }

    override fun successfulNetwork() {

    }

}