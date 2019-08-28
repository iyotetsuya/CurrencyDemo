package iyotetsuya.currencyconversion.ui.currency

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import iyotetsuya.currencyconversion.repository.CurrencyRepository
import iyotetsuya.currencyconversion.util.AbsentLiveData
import iyotetsuya.currencyconversion.vo.Currency
import iyotetsuya.currencyconversion.vo.Resource
import javax.inject.Inject

class CurrencyListViewModel @Inject constructor(currencyRepository: CurrencyRepository) :
    ViewModel() {
    private val _init = MutableLiveData<Boolean>()

    val currencies: LiveData<Resource<List<Currency>>> = Transformations
        .switchMap(_init) { init ->
            if (init == false) {
                AbsentLiveData.create()
            } else {
                currencyRepository.getCurrencies()
            }
        }

    fun init() {
        if (_init.value != true) {
            _init.postValue(true)
        }
    }

    fun retry() {

    }
}