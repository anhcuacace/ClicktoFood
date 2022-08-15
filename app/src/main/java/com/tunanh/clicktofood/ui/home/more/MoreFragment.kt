package com.tunanh.clicktofood.ui.home.more

import android.Manifest
import android.app.Activity
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Build
import android.provider.MediaStore
import androidx.activity.result.ActivityResult
import androidx.activity.result.ActivityResultCallback
import androidx.activity.result.contract.ActivityResultContracts
import com.bumptech.glide.Glide
import com.tunanh.clicktofood.R
import com.tunanh.clicktofood.data.local.model.User
import com.tunanh.clicktofood.databinding.FragmentMoreBinding
import com.tunanh.clicktofood.ui.base.BaseFragment
import com.tunanh.clicktofood.util.setOnSingClickListener
import java.io.IOException

class MoreFragment : BaseFragment<FragmentMoreBinding, MoreViewModel>() {
    private val accountAdapter = AccountAdapter()

    override fun layoutRes(): Int = R.layout.fragment_more
    override fun viewModelClass(): Class<MoreViewModel> = MoreViewModel::class.java

    override fun initView() {
        getInfo()
        accountList()
    }





    private fun getInfo() {
        viewModel.user.observe(this){
            context?.let { it1 -> Glide.with(it1).load(it.image).into(binding.avt) }
            binding.name.text=it.name
            binding.email.text=it.email
            binding.phone.text=it.phone
        }
    }

    private fun accountList() {
        binding.accountList.adapter = accountAdapter
        viewModel.mItemMoreList.observe(this) {
            accountAdapter.itemMore = it
        }
    }
}