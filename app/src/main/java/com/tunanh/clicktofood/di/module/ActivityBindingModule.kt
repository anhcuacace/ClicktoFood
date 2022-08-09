package com.tunanh.clicktofood.di.module


import com.tunanh.clicktofood.ui.intro.IntroFragment
import com.tunanh.clicktofood.ui.login.LoginFragment
import com.tunanh.clicktofood.ui.main.MainActivity
import com.tunanh.clicktofood.ui.splash.SplashFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivityBindingModule {

    @ContributesAndroidInjector
    abstract fun bindMainActivity(): MainActivity

    @ContributesAndroidInjector
    abstract fun bindSplashFragment(): SplashFragment

    @ContributesAndroidInjector
    abstract fun bindIntroFragment(): IntroFragment

    @ContributesAndroidInjector
    abstract fun bindLoginFragment(): LoginFragment
}