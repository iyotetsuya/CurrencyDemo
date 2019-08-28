package iyotetsuya.currencyconversion.di

import dagger.Module
import dagger.android.ContributesAndroidInjector
import iyotetsuya.currencyconversion.ui.currency.CalculatorFragment

//import iyotetsuya.currencyconversion.ui.currency.InputFragment

@Module
abstract class FragmentModule {
    @ContributesAndroidInjector
    abstract fun contributeCurrencyListFragment(): CalculatorFragment

//    @ContributesAndroidInjector
//    abstract fun contributeUserInputFragment(): InputFragment

}