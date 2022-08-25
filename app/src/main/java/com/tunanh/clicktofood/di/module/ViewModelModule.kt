package com.tunanh.clicktofood.di.module

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.tunanh.clicktofood.di.viewmodel.ViewModelFactory
import com.tunanh.clicktofood.di.viewmodel.ViewModelKey
import com.tunanh.clicktofood.ui.detail.DetailViewModel
import com.tunanh.clicktofood.ui.home.MainFragmentViewModel
import com.tunanh.clicktofood.ui.home.category.CategoryViewModel
import com.tunanh.clicktofood.ui.home.main.HomeViewModel
import com.tunanh.clicktofood.ui.home.more.MoreViewModel
import com.tunanh.clicktofood.ui.home.cart.CartViewModel
import com.tunanh.clicktofood.ui.home.more.profile.UpdateProfileViewModel
import com.tunanh.clicktofood.ui.home.more.wishlist.WishListViewModel
import com.tunanh.clicktofood.ui.intro.IntroViewModel
import com.tunanh.clicktofood.ui.login.LoginViewModel
import com.tunanh.clicktofood.ui.main.MainViewModel
import com.tunanh.clicktofood.ui.search.SearchViewModel
import com.tunanh.clicktofood.ui.splash.SplashViewModel
import com.tunanh.clicktofood.ui.temp.TempViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class ViewModelModule {

    @Binds
    internal abstract fun bindViewModelFactory(factory: ViewModelFactory): ViewModelProvider.Factory

    @Binds
    @IntoMap
    @ViewModelKey(MainViewModel::class)
    internal abstract fun mainViewModel(viewModel: MainViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(SplashViewModel::class)
    internal abstract fun splashViewModel(viewModel: SplashViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(IntroViewModel::class)
    internal abstract fun introViewModel(viewModel: IntroViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(LoginViewModel::class)
    internal abstract fun loginViewModel(viewModel: LoginViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(MainFragmentViewModel::class)
    internal abstract fun mainFragmentViewModel(viewModel: MainFragmentViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(HomeViewModel::class)
    internal abstract fun homeViewModel(viewModel: HomeViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(CategoryViewModel::class)
    internal abstract fun categoryViewModel(viewModel: CategoryViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(WishListViewModel::class)
    internal abstract fun wishListViewModel(viewModel: WishListViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(MoreViewModel::class)
    internal abstract fun moreViewModel(viewModel: MoreViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(UpdateProfileViewModel::class)
    internal abstract fun updateProfileViewModel(viewModel: UpdateProfileViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(DetailViewModel::class)
    internal abstract fun detailViewModel(viewModel: DetailViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(TempViewModel::class)
    internal abstract fun tempViewModel(viewModel: TempViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(SearchViewModel::class)
    internal abstract fun searchViewModel(viewModel: SearchViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(CartViewModel::class)
    internal abstract fun cartViewModel(viewModel: CartViewModel): ViewModel
}