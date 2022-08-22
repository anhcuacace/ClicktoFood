package com.tunanh.clicktofood

//import com.zing.zalo.zalosdk.oauth.ZaloSDKApplication
import com.tunanh.clicktofood.di.AppComponent
import com.tunanh.clicktofood.di.DaggerAppComponent
import dagger.android.AndroidInjector
import dagger.android.support.DaggerApplication
import timber.log.Timber

class BaseApp : DaggerApplication() {

    lateinit var instance: BaseApp

    override fun onCreate() {
        super.onCreate()
//        ZaloSDKApplication.wrap(this);
        instance = this
        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        }
    }

    override fun applicationInjector(): AndroidInjector<out DaggerApplication> {
        val component: AppComponent = DaggerAppComponent.builder().application(this).build()
        component.inject(this)
        return component
    }
}