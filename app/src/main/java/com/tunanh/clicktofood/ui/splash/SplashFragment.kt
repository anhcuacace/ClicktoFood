package com.tunanh.clicktofood.ui.splash

import android.os.Handler
import android.os.Looper
import android.view.View
import com.tunanh.clicktofood.R
import com.tunanh.clicktofood.databinding.FragmentSplashBinding
import com.tunanh.clicktofood.ui.base.BaseFragment
import com.tunanh.clicktofood.ui.main.MainActivity

class SplashFragment : BaseFragment<FragmentSplashBinding, SplashViewModel>() {
    override fun layoutRes(): Int = R.layout.fragment_splash

    override fun viewModelClass(): Class<SplashViewModel> = SplashViewModel::class.java

    override fun initView() {

    }

    private fun transition() {
        Handler(Looper.getMainLooper()).postDelayed({
            viewModel.intro.observe(this) {
                if (it) {
                    viewModel.user.observe(this) { it1 ->
                        if (it1) {
                            getNavController().navigate(R.id.action_splashFragment_to_mainFragment)

                        } else {
                            getNavController().navigate(R.id.action_splashFragment_to_loginFragment)
                        }
                    }

                } else {
                    getNavController().navigate(R.id.action_splashFragment_to_introFragment)
                }
            }
        }, 2000)
    }

    override fun networkFail() {
        binding.animationView.visibility = View.GONE
        (activity as MainActivity).showLoading()
    }

    override fun successfulNetwork() {
        (activity as MainActivity).hiddenLoading()
        transition()
    }


}