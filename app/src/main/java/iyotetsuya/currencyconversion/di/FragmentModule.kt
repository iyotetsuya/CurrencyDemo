package iyotetsuya.currencyconversion.di

//package iyotetsuya.currencyconversion.di

import dagger.Module
import dagger.android.ContributesAndroidInjector
import iyotetsuya.currencyconversion.ui.currency.CurrencyListFragment


@Module
abstract class FragmentModule {
    @ContributesAndroidInjector
    abstract fun contributeCurrencyListFragment(): CurrencyListFragment
//
//    @ContributesAndroidInjector
//    abstract fun contributeUserFragment(): UserFragment
//
//    @ContributesAndroidInjector
//    abstract fun contributeSearchFragment(): SearchFragment
}