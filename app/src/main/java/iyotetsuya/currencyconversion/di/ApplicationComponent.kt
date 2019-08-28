package iyotetsuya.currencyconversion.di


import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjectionModule
import dagger.android.AndroidInjector
import iyotetsuya.currencyconversion.CurrencyApp
import javax.inject.Singleton

@Singleton
@Component(modules = [AndroidInjectionModule::class, ApplicationModule::class, ActivityModule::class])
interface ApplicationComponent : AndroidInjector<CurrencyApp> {
    @Component.Builder
    interface Builder {
        @BindsInstance
        fun application(app: CurrencyApp): Builder

        fun build(): ApplicationComponent
    }

    override fun inject(app: CurrencyApp)
}