package iyotetsuya.currencyconversion.di

import dagger.Module
import dagger.android.ContributesAndroidInjector
import iyotetsuya.currencyconversion.ui.currency.CalculatorFragment

@Module
abstract class FragmentModule {
    @ContributesAndroidInjector
    abstract fun contributeCurrencyListFragment(): CalculatorFragment

}