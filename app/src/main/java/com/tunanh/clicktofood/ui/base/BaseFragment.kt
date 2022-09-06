package com.tunanh.clicktofood.ui.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import com.tunanh.clicktofood.listener.OnClickConfirmDialog
import com.tunanh.clicktofood.ui.custemview.FoodDialog
import com.tunanh.clicktofood.ui.main.MainActivity
import dagger.android.support.DaggerFragment
import javax.inject.Inject

abstract class BaseFragment<T : ViewDataBinding, M : BaseViewModel> : DaggerFragment() {
    protected lateinit var binding: T
    protected lateinit var viewModel: M

    @Inject
    protected lateinit var viewModelFactory: ViewModelProvider.Factory

    @LayoutRes
    protected abstract fun layoutRes(): Int
    protected abstract fun viewModelClass(): Class<M>
    protected abstract fun initView()


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, layoutRes(), container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.lifecycleOwner = viewLifecycleOwner
        viewModel = ViewModelProvider(this, viewModelFactory)[viewModelClass()]
        initView()
    }


    fun Fragment.getNavController(): NavController =
        NavHostFragment.findNavController(this)

    fun showDialog(
        title: String,
        content: String,
        rightButton: String,
        leftButton: String,
        onClickConfirmDialog: OnClickConfirmDialog
    ) {
        FoodDialog.Builder()
            .with(requireContext())
            .title(title)
            .content(content)
            .rightButton(rightButton)
            .leftButton(leftButton)
            .onClick(onClickConfirmDialog)
            .build()
    }
}