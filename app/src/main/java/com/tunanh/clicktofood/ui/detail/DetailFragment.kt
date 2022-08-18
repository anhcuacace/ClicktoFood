package com.tunanh.clicktofood.ui.detail

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.tunanh.clicktofood.R
import com.tunanh.clicktofood.databinding.FragmentDetailBinding
import com.tunanh.clicktofood.databinding.FragmentMainBinding
import com.tunanh.clicktofood.ui.base.BaseFragment
import com.tunanh.clicktofood.ui.home.MainFragmentViewModel

class DetailFragment : BaseFragment<FragmentDetailBinding, DetailViewModel>() {
    override fun layoutRes(): Int =R.layout.fragment_detail
    override fun viewModelClass(): Class<DetailViewModel> =DetailViewModel::class.java

    override fun initView() {

    }

    override fun backPress(): Boolean =false
}