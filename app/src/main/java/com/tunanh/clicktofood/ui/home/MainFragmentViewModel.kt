package com.tunanh.clicktofood.ui.home

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.google.android.gms.common.api.ApiException
import com.tunanh.clicktofood.data.remote.RemoteRepository
import com.tunanh.clicktofood.data.remote.model.Categories
import com.tunanh.clicktofood.ui.base.BaseViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import javax.inject.Inject

class MainFragmentViewModel @Inject constructor(
    private val remoteRepository: RemoteRepository
) : BaseViewModel() {
    var categoryList = MutableLiveData<Categories>()

    init {
        loadCategory()
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
}