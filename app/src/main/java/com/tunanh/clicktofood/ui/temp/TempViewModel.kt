package com.tunanh.clicktofood.ui.temp

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.google.android.gms.common.api.ApiException
import com.tunanh.clicktofood.data.local.LocalRepository
import com.tunanh.clicktofood.data.local.model.Food
import com.tunanh.clicktofood.data.remote.RemoteRepository
import com.tunanh.clicktofood.data.remote.model.Meals
import com.tunanh.clicktofood.ui.base.BaseViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

class TempViewModel @Inject constructor(
    private val remoteRepository: RemoteRepository,
    private val localRepository: LocalRepository
) : BaseViewModel() {
    var foodList = MutableLiveData<Meals>()
    fun callApi(it: String) {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val data = remoteRepository.getAllPhoToList(it)
                foodList.postValue(data)

            } catch (e: ApiException) {
                e.printStackTrace()
            }
        }
    }

    fun addFoodToDataBase(listFood: List<Food>) {
        viewModelScope.launch {
            for (food in listFood) {
                localRepository.insertOrUpdate(food)
            }
        }
    }
}