package iyotetsuya.currencyconversion

import android.app.Application
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasAndroidInjector
import iyotetsuya.currencyconversion.di.AppInjector
import javax.inject.Inject

class CurrencyApp : Application(), HasAndroidInjector {

    @Inject
    lateinit var androidInjector: DispatchingAndroidInjector<Any>

    override fun onCreate() {
        super.onCreate()
        AppInjector.init(this)
    }

    override fun androidInjector() = androidInjector

}
