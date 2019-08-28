package iyotetsuya.currencyconversion.di

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap
import iyotetsuya.currencyconversion.ui.currency.CurrencyListViewModel
import iyotetsuya.currencyconversion.ui.currency.InputViewModel
import iyotetsuya.currencyconversion.viewmodels.ViewModelFactory

@Suppress("unused")
@Module
abstract class ViewModelModule {
    @Binds
    @IntoMap
    @ViewModelKey(CurrencyListViewModel::class)
    abstract fun bindCurrencyListViewModel(currencyListViewModel: CurrencyListViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(InputViewModel::class)
    abstract fun bindInputViewModel(inputViewModel: InputViewModel): ViewModel

    @Binds
    abstract fun bindViewModelFactory(factory: ViewModelFactory): ViewModelProvider.Factory
}