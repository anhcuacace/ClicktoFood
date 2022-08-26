package com.tunanh.clicktofood.ui.search

import com.tunanh.clicktofood.R
import com.tunanh.clicktofood.databinding.FragmentSearchBinding
import com.tunanh.clicktofood.ui.base.BaseFragment

class SearchFragment : BaseFragment<FragmentSearchBinding, SearchViewModel>() {
    override fun layoutRes(): Int = R.layout.fragment_search

    override fun viewModelClass(): Class<SearchViewModel> = SearchViewModel::class.java

    override fun initView() {

    }
}