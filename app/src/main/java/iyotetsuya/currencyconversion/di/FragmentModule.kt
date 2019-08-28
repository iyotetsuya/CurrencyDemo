package iyotetsuya.currencyconversion.di

import dagger.Module
import dagger.android.ContributesAndroidInjector
import iyotetsuya.currencyconversion.ui.currency.CurrencyListFragment
import iyotetsuya.currencyconversion.ui.currency.InputFragment

@Module
abstract class FragmentModule {
    @ContributesAndroidInjector
    abstract fun contributeCurrencyListFragment(): CurrencyListFragment

    @ContributesAndroidInjector
    abstract fun contributeUserInputFragment(): InputFragment

}