package com.tunanh.clicktofood.ui.main

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.net.ConnectivityManager
import android.widget.Toast
import com.tunanh.clicktofood.R
import com.tunanh.clicktofood.databinding.ActivityMainBinding
import com.tunanh.clicktofood.ui.base.BaseActivity
import com.tunanh.clicktofood.util.hasNetworkConnection

class MainActivity : BaseActivity<ActivityMainBinding, MainViewModel>() {
    private val broadcastReceiver = object : BroadcastReceiver() {
        override fun onReceive(context: Context, intent: Intent?) {

            if (hasNetworkConnection(context)) {
                Toast.makeText(this@MainActivity, "Internet Connected", Toast.LENGTH_SHORT).show()
                viewModel.isNetworkConnection.value=true
            } else {
                Toast.makeText(this@MainActivity, "Internet Disconnected", Toast.LENGTH_SHORT)
                    .show()
                viewModel.isNetworkConnection.value=false
            }

        }

    }

    override fun layoutRes(): Int = R.layout.activity_main

    override fun viewModelClass(): Class<MainViewModel> = MainViewModel::class.java

    override fun initView() {

    }

    companion object {
        const val INTRO = "intro"
        const val EMAIL = "email"
        const val TOKEN = "token"
    }

    override fun onStart() {
        super.onStart()
        val intentFilter = IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION)
        registerReceiver(broadcastReceiver, intentFilter)
    }

    override fun onStop() {
        super.onStop()
        unregisterReceiver(broadcastReceiver)
    }
}