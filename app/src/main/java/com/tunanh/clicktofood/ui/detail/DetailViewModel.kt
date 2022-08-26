package com.tunanh.clicktofood.ui.detail

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.google.android.gms.common.api.ApiException
import com.tunanh.clicktofood.data.remote.RemoteRepository
import com.tunanh.clicktofood.data.remote.model.Meals
import com.tunanh.clicktofood.ui.base.BaseViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

class DetailViewModel @Inject constructor(
    private val remoteRepository: RemoteRepository
) : BaseViewModel() {
    var foodList = MutableLiveData<Meals>()
init {
    callApi()
}

    private fun callApi() {
        viewModelScope.launch (Dispatchers.IO){
            try {
                val data = remoteRepository.getAllPhoToList("Beef")
                foodList.postValue(data)
            } catch (e: ApiException) {
                e.printStackTrace()
            }
        }
    }
}