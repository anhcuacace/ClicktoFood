package com.tunanh.clicktofood.di.module


import com.tunanh.clicktofood.ui.detail.DetailFragment
import com.tunanh.clicktofood.ui.home.MainFragment
import com.tunanh.clicktofood.ui.home.category.CategoryFragment
import com.tunanh.clicktofood.ui.home.main.HomeFragment
import com.tunanh.clicktofood.ui.home.more.MoreFragment
import com.tunanh.clicktofood.ui.home.more.profile.UpdateProfileFragment
import com.tunanh.clicktofood.ui.home.wishlist.WishListFragment
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

    @ContributesAndroidInjector
    abstract fun bindMainFragment(): MainFragment

    @ContributesAndroidInjector
    abstract fun bindHomeFragment(): HomeFragment

    @ContributesAndroidInjector
    abstract fun bindCategoryFragment(): CategoryFragment

    @ContributesAndroidInjector
    abstract fun bindWishListFragment(): WishListFragment

    @ContributesAndroidInjector
    abstract fun bindMoreFragment(): MoreFragment

    @ContributesAndroidInjector
    abstract fun bindUpdateProfileFragment(): UpdateProfileFragment

    @ContributesAndroidInjector
    abstract fun bindDetailFragment(): DetailFragment
}