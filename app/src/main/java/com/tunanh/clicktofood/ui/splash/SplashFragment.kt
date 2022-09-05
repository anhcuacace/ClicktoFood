package com.tunanh.clicktofood.ui.splash

import android.os.Handler
import android.os.Looper
import android.view.View
import com.tunanh.clicktofood.R
import com.tunanh.clicktofood.databinding.FragmentSplashBinding
import com.tunanh.clicktofood.ui.base.BaseFragment
import com.tunanh.clicktofood.ui.main.MainActivity
import com.tunanh.clicktofood.util.hasNetworkConnection

class SplashFragment : BaseFragment<FragmentSplashBinding, SplashViewModel>() {
    override fun layoutRes(): Int = R.layout.fragment_splash

    override fun viewModelClass(): Class<SplashViewModel> = SplashViewModel::class.java

    override fun initView() {

        if (hasNetworkConnection(requireContext())) {
            transition()
        }else {
            binding.animationView.visibility=View.GONE
            Handler(Looper.getMainLooper()).postDelayed({
                (activity as MainActivity).finish()
            }, 10000)
        }

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

    override fun onDestroy() {
        super.onDestroy()
        print("==== destroy")
    }


}