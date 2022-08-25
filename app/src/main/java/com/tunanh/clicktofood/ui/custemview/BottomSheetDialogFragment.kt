package com.tunanh.clicktofood.ui.custemview

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.tunanh.clicktofood.R
import com.tunanh.clicktofood.data.local.model.Food
import com.tunanh.clicktofood.databinding.BottomSheetBinding
import com.tunanh.clicktofood.databinding.LayoutActionBarBinding
import com.tunanh.clicktofood.ui.main.MainActivity
import com.tunanh.clicktofood.util.setOnSingClickListener
import com.tunanh.clicktofood.util.shareLink

class BottomSheetDialogFragment(var food: Food):BottomSheetDialogFragment(){

private var binding:BottomSheetBinding?=null
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding= BottomSheetBinding.inflate(LayoutInflater.from(context))
        return binding!!.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding?.imgFood?.let {
            Glide.with(requireContext()).load(food.img).error(R.mipmap.ic_launcher).into(
                it
            )
        }
        binding?.tvNameItem?.text = food.title
        binding?.tvPrice?.text = "${food.cost} $"
        binding?.lnShare?.setOnSingClickListener { shareLink(food.img.toString(), requireContext()) }
        binding?.addToCard?.setOnSingClickListener {
            (activity as MainActivity).viewModel.addToCard(food)
            (activity as MainActivity).viewModel.isLoadCart.value=true

            dismiss()
        }
    }
}