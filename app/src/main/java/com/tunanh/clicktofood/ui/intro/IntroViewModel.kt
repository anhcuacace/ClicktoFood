package com.tunanh.clicktofood.ui.intro

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.tunanh.clicktofood.R
import com.tunanh.clicktofood.data.local.AppPreferences
import com.tunanh.clicktofood.data.local.model.IntroItem
import com.tunanh.clicktofood.ui.base.BaseViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

class IntroViewModel @Inject constructor(
    private val appPreferences: AppPreferences
) : BaseViewModel() {

    private var introItemList= mutableListOf<IntroItem>()
    var introList = MutableLiveData<List<IntroItem>>()

    init {
        addItem()

    }

    fun setIntro(){
        appPreferences.setIntro(true)
    }

    private fun addItem(){
        viewModelScope.launch {
            addToList("ORDER ONLINE","you can eat anything\nas long as L Food",R.drawable.intro3)
            addToList("SELECT FOOD","Just sit at home and L food take care",R.drawable.intro2)
            addToList("DELIVERY","Just sit at home and L food take care",R.drawable.intro3)
            introList.value=introItemList
        }
    }
    private fun addToList(title:String,description:String,img:Int){
        val introItem=IntroItem(title,description,img)
        introItemList.add(introItem)
    }
}