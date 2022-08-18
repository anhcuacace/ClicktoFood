package com.tunanh.clicktofood.ui.home.category

import androidx.lifecycle.ViewModelProvider
import com.tunanh.clicktofood.R
import com.tunanh.clicktofood.databinding.FragmentCategoryBinding
import com.tunanh.clicktofood.ui.base.BaseFragment
import com.tunanh.clicktofood.ui.home.main.CategoryHomeAdapter
import com.tunanh.clicktofood.ui.home.main.HomeViewModel


class CategoryFragment : BaseFragment<FragmentCategoryBinding, CategoryViewModel>() {
    override fun layoutRes(): Int = R.layout.fragment_category

    override fun viewModelClass(): Class<CategoryViewModel> = CategoryViewModel::class.java

    override fun initView() {
        val categoryHomeAdapter = CategoryHomeAdapter()
        val myViewModel: HomeViewModel =
            ViewModelProvider(this, viewModelFactory)[HomeViewModel::class.java]
        myViewModel.isLoadCategory.observe(this) {
            if (it) {
                myViewModel.categoryList.observe(this) { it1 ->
                    categoryHomeAdapter.categoryList = it1.Categories
                    binding.listCategory.adapter = categoryHomeAdapter
                }
            }
        }
    }
    override fun backPress(): Boolean = false
}