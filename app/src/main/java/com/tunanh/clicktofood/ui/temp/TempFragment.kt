package com.tunanh.clicktofood.ui.temp

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.tunanh.clicktofood.R
import com.tunanh.clicktofood.databinding.FragmentTempBinding
import com.tunanh.clicktofood.ui.base.BaseFragment

class TempFragment : BaseFragment<FragmentTempBinding,TempViewModel>() {
    override fun layoutRes(): Int =R.layout.fragment_temp

    override fun viewModelClass(): Class<TempViewModel> =TempViewModel::class.java

    override fun initView() {

    }

    override fun backPress()= false
}