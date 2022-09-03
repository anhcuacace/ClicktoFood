package com.tunanh.clicktofood.ui.main

import androidx.lifecycle.viewModelScope
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import com.tunanh.clicktofood.data.local.AppPreferences
import com.tunanh.clicktofood.data.local.LocalRepository
import com.tunanh.clicktofood.data.local.model.Food
import com.tunanh.clicktofood.ui.base.BaseViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

class MainViewModel @Inject constructor(
    appPreferences: AppPreferences,
    private val localRepository: LocalRepository
) : BaseViewModel() {
    var isLoadCart: (() -> Unit)? = null

    var isLoadProfile: (() -> Unit)? = null
    private var database1: DatabaseReference = Firebase.database.reference
    private val myReference = database1.child("app/user")
        .child(appPreferences.getToken()).child("card")

    fun updateLove(food: Food) {
        viewModelScope.launch {
            localRepository.insertOrUpdate(food)
            myReference.child(food.id.toString()).setValue(food)
        }
    }

    fun addToCard(food: Food) {

        viewModelScope.launch {
            val temp = localRepository.isRowIsExist(food.id)
            if (temp) {
                localRepository.updateFood(food)
            } else {
                localRepository.insertFood(food)
            }
//            myReference.child(count().toString()).setValue(food.id)
            myReference.child(food.id.toString()).setValue(food)
            isLoadCart?.invoke()
        }
    }
}