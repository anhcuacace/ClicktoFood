package com.tunanh.clicktofood.ui.login

import android.util.Log
import androidx.lifecycle.viewModelScope
import com.google.firebase.FirebaseException
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import com.tunanh.clicktofood.data.local.AppPreferences
import com.tunanh.clicktofood.data.local.LocalRepository
import com.tunanh.clicktofood.data.local.model.Food
import com.tunanh.clicktofood.data.local.model.User
import com.tunanh.clicktofood.ui.base.BaseViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

class LoginViewModel @Inject constructor(
    private val appPreferences: AppPreferences,
    private val localRepository: LocalRepository
) : BaseViewModel() {
    private var database1: DatabaseReference = Firebase.database.reference
    var loadDone:(()->Unit)?=null
    fun saveUser(
        email: String,
        name: String,
        img: String,
        phone: String,
        token: String
    ) {

        appPreferences.setEmail(email)
        appPreferences.setToken(token)
        viewModelScope.launch {
            val mUser = User(
                email = email,
                name = name,
                image = img,
                phone = phone
            )
            localRepository.insertUser(mUser)
        }
        getCart(token)
    }

    private fun getCart(token: String) {
        val array= ArrayList<Food>()
            try {
                val myReference = database1.child("app/user")
                    .child(token).child("card")
                myReference.addValueEventListener(object :ValueEventListener{
                    override fun onDataChange(snapshot: DataSnapshot) {
                        for (data in snapshot.children){
                            val foodData=data.value as HashMap<*, *>
                            val food=Food(
                                id = foodData["id"] as Long,
                                title = foodData["title"] as String,
                                cost = (foodData["cost"] as Long).toInt(),
                                like=foodData["like"] as Boolean,
                             star= foodData["star"] as Double,
                             img=foodData["img"]as String,
                             amount= (foodData["amount"] as Long).toInt()
                            )
                            array.add(food)
                        }
                        addToCart(array)
                    }

                    override fun onCancelled(error: DatabaseError) {
                        Log.e("LoginViewModel",error.message)
                    }

                })

            }catch (e:FirebaseException){
                Log.e("LoginViewModel",e.message.toString())
            }



    }

    private fun addToCart(food: List<Food>) {
        viewModelScope.launch {
            localRepository.addFood(food)
            loadDone?.invoke()
        }

    }

}