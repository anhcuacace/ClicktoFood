package com.tunanh.clicktofood.ui.home.main

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.google.android.gms.common.api.ApiException
import com.tunanh.clicktofood.data.remote.RemoteRepository
import com.tunanh.clicktofood.data.remote.model.Categories
import com.tunanh.clicktofood.data.remote.model.Meals
import com.tunanh.clicktofood.data.remote.model.Slider
import com.tunanh.clicktofood.ui.base.BaseViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import javax.inject.Inject

class HomeViewModel @Inject constructor(
    private val remoteRepository: RemoteRepository
) :
    BaseViewModel() {
    var sliderList = MutableLiveData<List<Slider>>()
    var categoryList = MutableLiveData<Categories>()
    var foodList = MutableLiveData<Meals>()

    init {
        loadSlider()
        loadCategory()
        loadRecyclerView()
    }

    private fun loadRecyclerView() {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val data = async { remoteRepository.getAllPhoToList("Dessert") }
                foodList.postValue(data.await())
            } catch (e: ApiException) {
                e.printStackTrace()
            }
        }
    }

    private fun loadCategory() {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val data = async { remoteRepository.getAllCategory() }
                categoryList.postValue(data.await())
            } catch (e: ApiException) {
                e.printStackTrace()
            }
        }
    }

    private fun loadSlider() {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val data = remoteRepository.getAllPhotos()
                sliderList.postValue(data)
            } catch (e: ApiException) {
                e.printStackTrace()
            }

        }
    }

}