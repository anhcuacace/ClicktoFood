package com.tunanh.clicktofood.ui.login

import androidx.lifecycle.viewModelScope
import com.google.android.gms.common.api.ApiException
import com.tunanh.clicktofood.data.local.AppPreferences
import com.tunanh.clicktofood.data.local.LocalRepository
import com.tunanh.clicktofood.data.local.model.CountId
import com.tunanh.clicktofood.data.local.model.Food
import com.tunanh.clicktofood.data.local.model.User
import com.tunanh.clicktofood.data.remote.RemoteRepository
import com.tunanh.clicktofood.ui.base.BaseViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.util.concurrent.ThreadLocalRandom
import javax.inject.Inject

class LoginViewModel @Inject constructor(
    private val appPreferences: AppPreferences,
    private val localRepository: LocalRepository,
    private val remoteRepository: RemoteRepository
) : BaseViewModel() {
//    private val listId=MutableLiveData<List<Long>>()

    init {

    }

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
        getId(token)
    }

    private fun getId(token: String) {
        viewModelScope.launch(Dispatchers.IO) {

            try {
                val data = async { remoteRepository.getIdFood(token) }
                addToCart(cart(data.await()))
            } catch (e: ApiException) {
                e.printStackTrace()
            }

        }


    }

    private fun addToCart(cart: List<CountId>) {
        viewModelScope.launch(Dispatchers.IO) {
            for (i in cart.indices) {
                val data = withContext(Dispatchers.IO) {
                    remoteRepository.getFood(cart[i].id)
                }
                val data1 = data.meals?.get(0)
                val food = Food(
                    data1?.id ?: 0,
                    title = data1?.title.toString(),
                    cost = ThreadLocalRandom.current().nextInt(20, 100),
                    star = ThreadLocalRandom.current().nextDouble(3.5, 5.0),
                    img = data1?.img,
                    amount = cart[i].amount
                )
                localRepository.insertFood(food)
            }
        }
    }

    private fun cart(data: List<Long>): List<CountId> {
        val array = ArrayList<CountId>()
        for (i in data.indices) {
            var count = 1
            for (j in 0 until i) {
                if (data[j] == data[i]) {
                    count += 1
                }
            }
            array.add(i, CountId(data[i], count))
        }
        return array
    }

}