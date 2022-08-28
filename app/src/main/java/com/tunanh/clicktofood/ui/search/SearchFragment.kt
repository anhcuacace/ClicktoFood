package com.tunanh.clicktofood.ui.search

import android.view.View
import android.widget.Toast
import com.tunanh.clicktofood.R
import com.tunanh.clicktofood.data.local.model.Food
import com.tunanh.clicktofood.data.local.model.KeyWorkSearch
import com.tunanh.clicktofood.databinding.FragmentSearchBinding
import com.tunanh.clicktofood.ui.base.BaseFragment
import com.tunanh.clicktofood.ui.custemview.BottomSheetDialogFragment
import com.tunanh.clicktofood.ui.main.MainActivity
import com.tunanh.clicktofood.util.setOnSingClickListener
import com.tunanh.clicktofood.util.showKeyboard

class SearchFragment : BaseFragment<FragmentSearchBinding, SearchViewModel>() {
    private val adapter=SearchFoodAdapter()
    private val historyAdapter=SearchHistoryAdapter()
    override fun layoutRes(): Int = R.layout.fragment_search

    override fun viewModelClass(): Class<SearchViewModel> = SearchViewModel::class.java

    override fun initView() {
        showKeyboard(binding.searchView.edtSearch!!,requireContext())

        //click
        binding.ivBack.setOnSingClickListener {
            getNavController().popBackStack()
        }
        binding.tvRemoveAllHistory.setOnSingClickListener {
            viewModel.removeAllHistory()
        }

        listSearch()
        binding.rvHistory.adapter=historyAdapter
        viewModel.historyList.observe(viewLifecycleOwner) {
            if (it == null || it.isEmpty()) {
                binding.tvHistoryTitle.visibility = View.GONE
                binding.tvRemoveAllHistory.visibility = View.GONE
                binding.rvHistory.visibility = View.GONE
            } else {
                historyAdapter.addHistoryList(it as ArrayList<KeyWorkSearch>)
                binding.tvHistoryTitle.visibility = View.VISIBLE
                binding.tvRemoveAllHistory.visibility = View.VISIBLE
                binding.rvHistory.visibility = View.VISIBLE
            }
        }

        historyAdapter.onClickItem={
            binding.searchView.setTextSearch(it.name)
            binding.searchView.requestFocusSearch()
        }
        historyAdapter.onClickDeleteItem={
            viewModel.removeHistory(it)

        }

    }

    private fun listSearch() {
        binding.listFood.adapter=adapter
        binding.searchView.addTextChange {
            if (it.isEmpty()) {
                binding.listFood.visibility = View.GONE
            } else {
                binding.listFood.visibility = View.VISIBLE
                viewModel.searchData(it)
            }
        }
        viewModel.foodList.observe(viewLifecycleOwner) {
            adapter.addFoodList(it)
        }
        adapter.onClickItem={
            viewModel.addHistory(binding.searchView.getTextSearch())
            val food=Food(it.id,it.title,it.cost,it.star, img = it.img)
            val bottomSheetDialogFragment= BottomSheetDialogFragment(food = food)
            bottomSheetDialogFragment.show(childFragmentManager,"ActionButton")

        }
        adapter.onClickAdd={
            val food=Food(it.id,it.title,it.cost,it.star, img = it.img)
            (activity as MainActivity).viewModel.addToCard(food)
            Toast.makeText(requireContext(), requireContext().getString(R.string.addfood), Toast.LENGTH_SHORT).show()
            viewModel.addHistory(binding.searchView.getTextSearch())
        }
    }

}