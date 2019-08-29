package iyotetsuya.currencyconversion.ui.currency

import androidx.lifecycle.*
import iyotetsuya.currencyconversion.repository.CurrencyRepository
import iyotetsuya.currencyconversion.util.AbsentLiveData
import iyotetsuya.currencyconversion.vo.CurrencyRate
import iyotetsuya.currencyconversion.vo.Resource
import iyotetsuya.currencyconversion.vo.SupportedCurrency
import javax.inject.Inject

class CalculatorViewModel @Inject constructor(currencyRepository: CurrencyRepository) :
    ViewModel() {

    private val selectedCurrency = MutableLiveData<SupportedCurrency>()

    val supportedCurrencies: LiveData<Resource<List<SupportedCurrency>>> =
        currencyRepository.getCurrencies()

    val currencyRateResource: LiveData<Resource<List<CurrencyRate>>> = Transformations
        .switchMap(selectedCurrency) { currency ->
            if (currency == null) {
                AbsentLiveData.create()
            } else {
                currencyRepository.getCurrencyRateList(currency.code)
            }
        }
    var input = MutableLiveData<String>("0")

    val exchangeResultList = Transformations.switchMap(input) { value ->
        currencyRateResource.map { res ->
            res.data?.map { currencyRate ->
                ExchangeResult(
                    currencyRate.target,
                    currencyRate.rate * (value.toFloatOrNull() ?: 0F)
                )
            }
        }
    }

    fun onCurrencySelected(position: Int) {
        selectedCurrency.value = supportedCurrencies.value?.data?.get(position)
    }

    fun retry() {
        selectedCurrency.value?.let {
            selectedCurrency.value = it
        }
    }

    data class ExchangeResult(val currencyCode: String, val value: Float)

}