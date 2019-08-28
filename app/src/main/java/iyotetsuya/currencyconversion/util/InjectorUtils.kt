package iyotetsuya.currencyconversion.util

import android.content.Context
import iyotetsuya.currencyconversion.db.AppDb
import iyotetsuya.currencyconversion.repository.CurrencyRepository
//import iyotetsuya.currencyconversion.viewmodels.CurrencyListViewModelFactory

//object InjectorUtils {
//    private fun getCurrencyRepository(context: Context): CurrencyRepository {
//        return CurrencyRepository.getInstance(
//            AppDb.getInstance(context.applicationContext).currencyDao()
//        )
//    }
//
//    fun provideCurrencyListViewModelFactory(context: Context): CurrencyListViewModelFactory {
//        val repository = getCurrencyRepository(context)
//        return CurrencyListViewModelFactory(repository)
//    }
//
//}