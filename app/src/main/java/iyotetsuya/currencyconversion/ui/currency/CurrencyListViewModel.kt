package iyotetsuya.currencyconversion.ui.currency

import androidx.lifecycle.ViewModel
import iyotetsuya.currencyconversion.repository.CurrencyRepository
import javax.inject.Inject

class CurrencyListViewModel @Inject constructor(currencyRepository: CurrencyRepository) :
    ViewModel() {
    val currencies = currencyRepository.getCurrencies()
}