package com.tunanh.clicktofood.ui.home.more

import android.net.Uri
import com.bumptech.glide.Glide
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import com.tunanh.clicktofood.R
import com.tunanh.clicktofood.data.local.model.User
import com.tunanh.clicktofood.databinding.FragmentMoreBinding
import com.tunanh.clicktofood.listener.OnClickConfirmDialog
import com.tunanh.clicktofood.ui.base.BaseFragment
import com.tunanh.clicktofood.ui.main.MainActivity
import com.tunanh.clicktofood.util.openPlaystore
import com.tunanh.clicktofood.util.openWebsite
import com.tunanh.clicktofood.util.setOnSingClickListener
import com.tunanh.clicktofood.util.shareApp

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
        accountAdapter.onClickItem = {
            when (it) {
                1 -> {
                    openPlaystore(requireContext())
                }
                2 -> {
                    showDialog("Thông báo",
                        "Tính năng đang phát triển",
                        "Đồng ý",
                        "",
                        object : OnClickConfirmDialog {
                            override fun onClickRightButton() {
                            }

                            override fun onClickLeftButton() {
                            }

                        })
                }
            }
        }

    }

    private fun editProfile() {
        binding.editProfile.setOnSingClickListener {
            getNavController().navigate(
                R.id.action_mainFragment_to_updateProfileFragment2
            )
        }
    }


    private fun getInfo() {
        viewModel.user.observe(viewLifecycleOwner) {
            user = it
            context?.let { it1 ->
                Glide.with(it1).load(Uri.parse(user?.image)).error(R.drawable.ic_account_circle)
                    .into(binding.avt)
            }
            binding.name.text = user?.name ?: "Name"
            binding.email.text = user?.email
            binding.phone.text = user?.phone ?: "Phone"
        }
        (activity as MainActivity).viewModel.isLoadProfile = {
            viewModel.getUser()
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
                0 -> {
                    getNavController().navigate(
                        R.id.action_mainFragment_to_wishListFragment
                    )
                }
                3 -> {
                    openWebsite("https://messenger.com/t/100009599087926", requireContext())
                }
                6 -> {
                    shareApp(requireContext())
                }
                9 -> {
                    showDialog("Cảnh Báo",
                        "Bạn chắc chắn muốn đăng xuất",
                        "Đồng ý",
                        "Hủy",
                        object : OnClickConfirmDialog {
                            override fun onClickRightButton() {
                                logOut()
                            }

                            override fun onClickLeftButton() {
                            }

                        })
                }
                1, 2, 4, 5, 7, 8 -> {
                    showDialog("Thông báo",
                        "Tính năng đang phát triển",
                        "Đồng ý",
                        "",
                        object : OnClickConfirmDialog {
                            override fun onClickRightButton() {
                            }

                            override fun onClickLeftButton() {
                            }

                        })
                }
            }
        }
    }

    private fun logOut() {
        Firebase.auth.signOut()
        viewModel.logOut()
        getNavController().navigate(
            R.id.action_mainFragment_to_loginFragment
        )
    }



}