package com.tunanh.clicktofood.ui.splash

import android.os.Handler
import android.os.Looper
import android.view.View
import android.widget.Toast
import androidx.lifecycle.viewModelScope
import androidx.navigation.fragment.findNavController
import com.tunanh.clicktofood.R
import com.tunanh.clicktofood.databinding.FragmentSplashBinding
import com.tunanh.clicktofood.ui.base.BaseFragment
import com.tunanh.clicktofood.ui.main.MainActivity
import com.tunanh.clicktofood.util.hasNetworkConnection
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class SplashFragment : BaseFragment<FragmentSplashBinding, SplashViewModel>() {
    override fun layoutRes(): Int = R.layout.fragment_splash

    override fun viewModelClass(): Class<SplashViewModel> = SplashViewModel::class.java

    override fun initView() {
        if (hasNetworkConnection(requireContext())) {
            transition()
        } else {
            viewModel.viewModelScope.launch(Dispatchers.Main) {
                delay(2000)
                binding.animationView.visibility = View.GONE
                Toast.makeText(requireContext(), getString(R.string.network_fail), Toast.LENGTH_LONG)
                    .show()
                (activity as MainActivity).showLoading()
            }
            viewModel.viewModelScope.launch {
                repeat(1000) {
                    if (hasNetworkConnection(requireContext())) {
                        Toast.makeText(requireContext(), getString(R.string.connected), Toast.LENGTH_SHORT).show()
                        (activity as MainActivity).hiddenLoading()
                        transition()
                    }
                    delay(100)
                }
                backPress()
            }

        }
    }

    private fun transition() {
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

    override fun backPress(): Boolean = false
}