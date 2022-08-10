package com.tunanh.clicktofood.ui.home.more

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.tunanh.clicktofood.R
import com.tunanh.clicktofood.databinding.FragmentMoreBinding
import com.tunanh.clicktofood.ui.base.BaseFragment

class MoreFragment : BaseFragment<FragmentMoreBinding, MoreViewModel>() {
    override fun layoutRes(): Int =R.layout.fragment_more

    override fun viewModelClass(): Class<MoreViewModel> =MoreViewModel::class.java

    override fun initView() {

    }
}