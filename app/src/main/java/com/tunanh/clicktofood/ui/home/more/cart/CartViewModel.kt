package com.tunanh.clicktofood.ui.home.more.cart

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.tunanh.clicktofood.data.local.LocalRepository
import com.tunanh.clicktofood.data.local.model.Food
import com.tunanh.clicktofood.ui.base.BaseViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

class CartViewModel  @Inject constructor(private val localRepository: LocalRepository) : BaseViewModel() {
    val cart = MutableLiveData<List<Food>>()
    init {
        addToCart()
    }

    private fun addToCart() {
        viewModelScope.launch {
            val data=localRepository.getAllFood()
            cart.postValue(data)
        }
    }
}