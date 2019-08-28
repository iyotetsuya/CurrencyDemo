package iyotetsuya.currencyconversion.ui.currency

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import iyotetsuya.currencyconversion.repository.CurrencyRepository
import iyotetsuya.currencyconversion.util.AbsentLiveData
import iyotetsuya.currencyconversion.vo.Quote
import iyotetsuya.currencyconversion.vo.Resource
import javax.inject.Inject

class InputViewModel @Inject constructor(currencyRepository: CurrencyRepository) :
    ViewModel() {


    var inputValue = MutableLiveData<String>()

    private val _currencyCode = MutableLiveData<String>()

    val quotes: LiveData<Resource<List<Quote>>> = Transformations
        .switchMap(_currencyCode) { currencyCode ->
            if (currencyCode == null) {
                AbsentLiveData.create()
            } else {
                currencyRepository.getQuotes(currencyCode)
            }
        }

    fun setCurrency(currencyCode: String) {
        if (_currencyCode.value != currencyCode) {
            _currencyCode.postValue(currencyCode)
        }
    }

    init {
        inputValue.value = "0"
    }
}