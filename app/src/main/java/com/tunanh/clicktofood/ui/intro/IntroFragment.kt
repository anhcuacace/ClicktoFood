package com.tunanh.clicktofood.ui.intro

import androidx.viewpager2.widget.ViewPager2
import com.tunanh.clicktofood.R
import com.tunanh.clicktofood.databinding.FragmentIntroBinding
import com.tunanh.clicktofood.ui.base.BaseFragment
import com.tunanh.clicktofood.util.setOnSingClickListener

class IntroFragment : BaseFragment<FragmentIntroBinding, IntroViewModel>() {
    private val adapter = IntroAdapter()
    override fun layoutRes(): Int = R.layout.fragment_intro

    override fun viewModelClass(): Class<IntroViewModel> = IntroViewModel::class.java

    override fun initView() {
        adapter.onItemClick = { _, _ ->
            //mở link nè
        }
        viewModel.introList.observe(this) {
            adapter.itemtList = it
        }
        binding.screenViewpager.adapter = adapter

        binding.screenViewpager.registerOnPageChangeCallback(object :
            ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
                if (binding.screenViewpager.currentItem == 2) {
                    changButton()
                } else {
                    binding.btn.text = resources.getText(R.string.next)
                    binding.btn.textSize = 20F
                    binding.btn.setOnSingClickListener {
                        binding.screenViewpager.apply {
                            beginFakeDrag()
                            fakeDragBy(-200f)
                            endFakeDrag()
                        }
                    }
                }
            }
        })
    }

    private fun changButton() {
        binding.btn.text = resources.getText(R.string.Getstarted)
        binding.btn.textSize = 14F
        binding.btn.setOnSingClickListener {
            viewModel.setIntro()
//            editor.putBoolean("intro",true)
//            editor.apply()
//            editor.commit()
            getNavController().navigate(
                R.id.action_introFragment_to_loginFragment
            )
        }
    }



}