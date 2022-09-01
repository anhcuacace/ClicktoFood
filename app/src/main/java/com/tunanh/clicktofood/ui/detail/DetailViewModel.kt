package com.tunanh.clicktofood.ui.detail

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.tunanh.clicktofood.data.local.LocalRepository
import com.tunanh.clicktofood.data.local.model.Food
import com.tunanh.clicktofood.ui.base.BaseViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

class DetailViewModel @Inject constructor(
   private val localRepository: LocalRepository
) : BaseViewModel() {
    var foodList = MutableLiveData<List<Food>>()
init {
    loadFood()
}

    private fun loadFood() {
        viewModelScope.launch {
            foodList.value=localRepository.getAllFood()
        }
    }


}