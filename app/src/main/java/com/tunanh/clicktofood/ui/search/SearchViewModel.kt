package com.tunanh.clicktofood.ui.search

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.tunanh.clicktofood.data.local.LocalRepository
import com.tunanh.clicktofood.data.local.model.FoodData
import com.tunanh.clicktofood.data.local.model.KeyWorkSearch
import com.tunanh.clicktofood.ui.base.BaseViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

class SearchViewModel  @Inject constructor(private val localRepository: LocalRepository) : BaseViewModel() {

    var foodList= MutableLiveData<List<FoodData>>()
    var historyList=MutableLiveData<List<KeyWorkSearch>>()

    init {
        loadHistory()
    }

    private fun loadHistory() {
        viewModelScope.launch {
            historyList.value = localRepository.getHistoryList()
        }
    }

    fun removeHistory(data: KeyWorkSearch) {
        viewModelScope.launch{
            localRepository.deleteHistory(data)
            historyList.value = localRepository.getHistoryList()
        }

    }
    fun searchData(name: String) {
        viewModelScope.launch {
            val data = localRepository.findFoodByName(name)
            foodList.postValue(data)
        }


    }

    fun addHistory(text: String) {
        viewModelScope.launch {
            if (text.isNotEmpty()) {
                val model = localRepository.getHistoryByName(text)
                if (model != null) {
                    localRepository.deleteHistory(model)
                }
                val history = KeyWorkSearch().apply {
                    this.name = text
                }
                localRepository.insertHistory(history)
                historyList.value = localRepository.getHistoryList()
            }
        }

    }
    fun removeAllHistory() {
        viewModelScope.launch {
            localRepository.deleteAll()
            historyList.value = ArrayList()
        }
    }
}