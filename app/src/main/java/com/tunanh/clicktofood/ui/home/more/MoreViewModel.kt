package com.tunanh.clicktofood.ui.home.more

import android.content.Context
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.tunanh.clicktofood.R
import com.tunanh.clicktofood.data.local.LocalDatabase
import com.tunanh.clicktofood.data.local.model.User
import com.tunanh.clicktofood.ui.base.BaseViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

class MoreViewModel @Inject constructor(
    private val context: Context,
    private val localDatabase: LocalDatabase
) : BaseViewModel() {
    private var itemMoreList = mutableListOf<ItemMore>()
    var mItemMoreList = MutableLiveData<List<ItemMore>>()
    var user = MutableLiveData<User>()

    init {
        addAccItem()

        getUser()
    }


    private fun addAccItem() {
        viewModelScope.launch {
            addToList(R.drawable.ic_cart, context.getString(R.string.myorders))
            addToList(R.drawable.percent, context.getString(R.string.promos))
            addToList(R.drawable.payments, context.getString(R.string.payment_methosds))
            addToList(R.drawable.ic_help, context.getString(R.string.help))
            addToList(R.drawable.language, context.getString(R.string.language))
            addToList(R.drawable.ic_save, context.getString(R.string.save))
            addToList(R.drawable.invite_frends, context.getString(R.string.invite_friend))
            addToList(R.drawable.fingerprint, context.getString(R.string.quick_login))
            addToList(R.drawable.notifications, context.getString(R.string.notifications))
            addToList(R.drawable.logout, context.getString(R.string.logout))
            mItemMoreList.value = itemMoreList
        }
    }

    private fun addToList(img: Int, text: String) {
        val item = ItemMore(img, text)
        itemMoreList.add(item)
    }

    private fun getUser() {
        viewModelScope.launch {
            user.value = localDatabase.userDao().getUser()[0]
        }
    }
}