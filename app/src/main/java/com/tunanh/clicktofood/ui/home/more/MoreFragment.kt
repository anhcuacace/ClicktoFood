package com.tunanh.clicktofood.ui.home.more

import android.net.Uri
import androidx.navigation.fragment.findNavController
import com.bumptech.glide.Glide
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import com.tunanh.clicktofood.R
import com.tunanh.clicktofood.data.local.model.User
import com.tunanh.clicktofood.databinding.FragmentMoreBinding
import com.tunanh.clicktofood.ui.base.BaseFragment
import com.tunanh.clicktofood.util.setOnSingClickListener

class MoreFragment : BaseFragment<FragmentMoreBinding, MoreViewModel>() {
    companion object {
        fun newInstance() = MoreFragment()
    }


    private var user: User? = null
    override fun layoutRes(): Int = R.layout.fragment_more
    override fun viewModelClass(): Class<MoreViewModel> = MoreViewModel::class.java

    override fun initView() {
        getInfo()
        accountList()
        generalList()
        editProfile()
    }

    private fun generalList() {
        val accountAdapter = AccountAdapter()

        viewModel.mItemMoreList2.observe(this) {
            accountAdapter.itemMore = it
            binding.generalList.adapter = accountAdapter
        }

    }

    private fun editProfile() {
        binding.editProfile.setOnSingClickListener {
            findNavController().navigate(
                R.id.action_mainFragment_to_updateProfileFragment2
            )
        }
    }


    private fun getInfo() {
        viewModel.user.observe(this) {
            user = it
            context?.let { it1 ->
                Glide.with(it1).load(Uri.parse(user?.image)).error(R.drawable.ic_account_circle)
                    .into(binding.avt)
            }
            binding.name.text = user?.name
            binding.email.text = user?.email
            binding.phone.text = user?.phone
        }
    }

    private fun accountList() {
        val accountAdapter = AccountAdapter()
        viewModel.mItemMoreList.observe(this) {
            accountAdapter.itemMore = it
            binding.accountList.adapter = accountAdapter
        }
        accountAdapter.onClickItem = {
            when (it) {
                9 -> {
                    Firebase.auth.signOut()
                    viewModel.logOut()
                    findNavController().navigate(
                        R.id.action_mainFragment_to_loginFragment
                    )
                }
            }
        }
    }

    override fun backPress(): Boolean = false
}