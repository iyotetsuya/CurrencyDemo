package iyotetsuya.currencyconversion.di

import dagger.Module
import dagger.android.ContributesAndroidInjector
import iyotetsuya.currencyconversion.MainActivity


@Module
abstract class ActivityModule {

    @ContributesAndroidInjector(modules = [FragmentModule::class])
    abstract fun contributeMainActivity(): MainActivity


}