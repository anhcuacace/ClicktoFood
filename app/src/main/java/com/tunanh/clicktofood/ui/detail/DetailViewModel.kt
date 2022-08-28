package com.tunanh.clicktofood.ui.detail

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.tunanh.clicktofood.data.local.LocalRepository
import com.tunanh.clicktofood.data.local.model.FoodData
import com.tunanh.clicktofood.ui.base.BaseViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

class DetailViewModel @Inject constructor(
   private val localRepository: LocalRepository
) : BaseViewModel() {
    var foodList = MutableLiveData<List<FoodData>>()
init {
    loadFood()
}

    private fun loadFood() {
        viewModelScope.launch {
            foodList.value=localRepository.getAllFoodData()
        }
    }


}