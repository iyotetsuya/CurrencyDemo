package iyotetsuya.currencyconversion.di

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap
import iyotetsuya.currencyconversion.ui.currency.CalculatorViewModel
import iyotetsuya.currencyconversion.ui.currency.InputViewModel
import iyotetsuya.currencyconversion.viewmodels.ViewModelFactory

@Suppress("unused")
@Module
abstract class ViewModelModule {
    @Binds
    @IntoMap
    @ViewModelKey(CalculatorViewModel::class)
    abstract fun bindCurrencyListViewModel(calculatorViewModel: CalculatorViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(InputViewModel::class)
    abstract fun bindInputViewModel(inputViewModel: InputViewModel): ViewModel

    @Binds
    abstract fun bindViewModelFactory(factory: ViewModelFactory): ViewModelProvider.Factory
}