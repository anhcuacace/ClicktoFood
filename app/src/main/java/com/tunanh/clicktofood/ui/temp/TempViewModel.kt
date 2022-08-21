package com.tunanh.clicktofood.ui.temp

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.google.android.gms.common.api.ApiException
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import com.tunanh.clicktofood.data.local.AppPreferences
import com.tunanh.clicktofood.data.local.LocalDatabase
import com.tunanh.clicktofood.data.local.model.Food
import com.tunanh.clicktofood.data.remote.RemoteRepository
import com.tunanh.clicktofood.data.remote.model.Meals
import com.tunanh.clicktofood.ui.base.BaseViewModel
import com.tunanh.clicktofood.util.count
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

class TempViewModel @Inject constructor(
    private val appPreferences: AppPreferences,
    private val remoteRepository: RemoteRepository,
    private val localDatabase: LocalDatabase
) : BaseViewModel() {
    private var database: DatabaseReference = Firebase.database.reference

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

    fun addToCard(food: Food) {
        val myReference = database.child("app/user").child(appPreferences.getToken()).child("card")
        viewModelScope.launch {
            localDatabase.foodDao().addFood(food)
            myReference.child(count().toString()).setValue(food.id)
        }

    }

}