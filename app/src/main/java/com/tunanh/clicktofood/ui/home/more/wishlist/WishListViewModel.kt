package com.tunanh.clicktofood.ui.home.more.wishlist

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.tunanh.clicktofood.data.local.LocalRepository
import com.tunanh.clicktofood.data.local.model.Food
import com.tunanh.clicktofood.ui.base.BaseViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

class WishListViewModel @Inject constructor(
    private val localRepository: LocalRepository
) : BaseViewModel() {
    var withList = MutableLiveData<List<Food>>()

    init {
        loadWishList()

    }

    private fun loadWishList() {
        viewModelScope.launch {
            val data = localRepository.getWithList()
            withList.postValue(data)
        }
    }

}