package iyotetsuya.currencyconversion.ui.currency

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import iyotetsuya.currencyconversion.repository.CurrencyRepository
import iyotetsuya.currencyconversion.util.AbsentLiveData
import iyotetsuya.currencyconversion.vo.Currency
import iyotetsuya.currencyconversion.vo.CurrencyRate
import iyotetsuya.currencyconversion.vo.Resource
import javax.inject.Inject

class CalculatorViewModel @Inject constructor(currencyRepository: CurrencyRepository) :
    ViewModel() {

    val supportedCurrencies: LiveData<Resource<List<Currency>>> =
        currencyRepository.getCurrencies()

    //    private val _currencyCode = MutableLiveData<String>()
    private val selectedCurrency = MutableLiveData<Currency>()

    val quotes: LiveData<Resource<List<CurrencyRate>>> = Transformations
        .switchMap(selectedCurrency) { currency ->
            if (currency == null) {
                AbsentLiveData.create()
            } else {
                currencyRepository.getCurrencyRateList(currency.code)
            }
        }


    fun onCurrencySelected(position: Int) {
        selectedCurrency.value = supportedCurrencies.value?.data?.get(position)
    }


}