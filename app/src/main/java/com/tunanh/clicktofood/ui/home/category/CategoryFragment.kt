package com.tunanh.clicktofood.ui.home.category

import com.tunanh.clicktofood.R
import com.tunanh.clicktofood.databinding.FragmentCategoryBinding
import com.tunanh.clicktofood.ui.base.BaseFragment

class CategoryFragment : BaseFragment<FragmentCategoryBinding, CategoryViewModel>() {
    override fun layoutRes(): Int =R.layout.fragment_category

    override fun viewModelClass(): Class<CategoryViewModel> =CategoryViewModel::class.java

    override fun initView() {

    }
    override fun backPress(): Boolean = false
}