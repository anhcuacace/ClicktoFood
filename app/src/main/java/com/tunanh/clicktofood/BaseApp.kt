package com.tunanh.clicktofood

//import com.zing.zalo.zalosdk.oauth.ZaloSDKApplication
import com.google.firebase.FirebaseApp
import com.google.firebase.analytics.FirebaseAnalytics
import com.google.firebase.analytics.ktx.analytics
import com.google.firebase.ktx.Firebase
import com.google.firebase.remoteconfig.FirebaseRemoteConfig
import com.google.firebase.remoteconfig.FirebaseRemoteConfigSettings
import com.tunanh.clicktofood.di.AppComponent
import com.tunanh.clicktofood.di.DaggerAppComponent
import dagger.android.AndroidInjector
import dagger.android.support.DaggerApplication
import timber.log.Timber

class BaseApp : DaggerApplication() {
    lateinit var instance: BaseApp
    private var remoteConfig: FirebaseRemoteConfig? = null
    private var mFirebaseAnalytics: FirebaseAnalytics? = null
    override fun onCreate() {
        super.onCreate()
//        ZaloSDKApplication.wrap(this);
        instance = this
        FirebaseApp.initializeApp(applicationContext)
        initConfig()
        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        }
    }
    fun getFirebaseRemote() : FirebaseRemoteConfig {
        if (remoteConfig == null) {
            initConfig()
        }
        return remoteConfig!!
    }

    override fun applicationInjector(): AndroidInjector<out DaggerApplication> {
        val component: AppComponent = DaggerAppComponent.builder().application(this).build()
        component.inject(this)
        return component
    }
    private fun initConfig() {
        remoteConfig = FirebaseRemoteConfig.getInstance()
        remoteConfig?.setConfigSettingsAsync(
            FirebaseRemoteConfigSettings.Builder()
            .setMinimumFetchIntervalInSeconds(60).build())
        remoteConfig?.setDefaultsAsync(R.xml.remote_config_defaults)
        mFirebaseAnalytics = Firebase.analytics
    }
}