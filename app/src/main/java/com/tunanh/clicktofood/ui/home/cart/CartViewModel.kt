package com.tunanh.clicktofood.ui.home.cart

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import com.tunanh.clicktofood.data.local.AppPreferences
import com.tunanh.clicktofood.data.local.LocalRepository
import com.tunanh.clicktofood.data.local.model.Food
import com.tunanh.clicktofood.ui.base.BaseViewModel
import kotlinx.coroutines.launch
import java.util.*
import javax.inject.Inject
import kotlin.collections.ArrayList

class CartViewModel @Inject constructor(
    private val localRepository: LocalRepository,
    appPreferences: AppPreferences
) :
    BaseViewModel() {
    private var itemFood: Food? = null
    val cart = MutableLiveData<List<Food>>()
    private val random = UUID.randomUUID().toString()
    private var database1: DatabaseReference = Firebase.database.reference
    private val myReferenceOrder = database1.child("app/user")
        .child(appPreferences.getToken()).child("order")
    private val myReferenceCart = database1.child("app/user")
        .child(appPreferences.getToken()).child("card")
    var loadDone: (() -> Unit)? = null

    init {
        addToCart()
    }

    fun addToCart() {
        viewModelScope.launch {
            val data = localRepository.getCard()
            cart.postValue(data)
        }
    }


    fun deleteCart(id: Long) {
        viewModelScope.launch {
            localRepository.deleteById(id)
        }
    }

    fun placeOrder(array: List<Food>) {

        viewModelScope.launch {
            for (food in array) {
                itemFood = food
                itemFood?.amount = 0
                localRepository.updateFood(itemFood!!)
                myReferenceCart.setValue(itemFood)
            }
        }
        myReferenceOrder.child(random).setValue(array).addOnCompleteListener {
            loadDone?.invoke()
        }

    }
}