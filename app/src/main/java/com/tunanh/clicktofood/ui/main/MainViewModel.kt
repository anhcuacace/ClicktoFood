package com.tunanh.clicktofood.ui.main

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import com.tunanh.clicktofood.data.local.AppPreferences
import com.tunanh.clicktofood.data.local.LocalRepository
import com.tunanh.clicktofood.data.local.model.Food
import com.tunanh.clicktofood.ui.base.BaseViewModel
import com.tunanh.clicktofood.util.count
import kotlinx.coroutines.launch
import javax.inject.Inject

class MainViewModel @Inject constructor(
    private val appPreferences: AppPreferences,
    private val localRepository: LocalRepository
) : BaseViewModel() {
    var isLoadCart=MutableLiveData<Boolean>()
    var isLoadProfile=MutableLiveData<Boolean>()
    private var database: DatabaseReference = Firebase.database.reference
    fun addToCard(food: Food) {
        val myReference = database.child("app/user").child(appPreferences.getToken()).child("card")
        viewModelScope.launch {
            val temp = localRepository.isRowIsExist(food.id)
            if (temp) {

                localRepository.updateFood(food.also {
                    it.amount = it.amount + 1
                })
            } else {
                localRepository.insertFood(food)
            }
            myReference.child(count().toString()).setValue(food.id)
        }
    }
}