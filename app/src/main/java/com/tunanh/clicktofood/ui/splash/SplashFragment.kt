package com.tunanh.clicktofood.ui.splash

import android.os.Handler
import android.os.Looper
import androidx.navigation.fragment.findNavController
import com.tunanh.clicktofood.R
import com.tunanh.clicktofood.databinding.FragmentSplashBinding
import com.tunanh.clicktofood.ui.base.BaseFragment

class SplashFragment : BaseFragment<FragmentSplashBinding, SplashViewModel>() {
    override fun layoutRes(): Int = R.layout.fragment_splash

    override fun viewModelClass(): Class<SplashViewModel> = SplashViewModel::class.java

    override fun initView() {
        Handler(Looper.getMainLooper()).postDelayed({
            viewModel.intro.observe(this) {
                if (it) {
                    viewModel.user.observe(this) { it1 ->
                        if (it1) {
                            findNavController().navigate(R.id.action_splashFragment_to_mainFragment)

                        } else {
                            findNavController().navigate(R.id.action_splashFragment_to_loginFragment)
                        }
                    }

                } else {
                    findNavController().navigate(R.id.action_splashFragment_to_introFragment)
                }
            }
        }, 2000)
    }
}