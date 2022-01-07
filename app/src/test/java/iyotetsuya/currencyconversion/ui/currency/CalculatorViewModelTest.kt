package iyotetsuya.currencyconversion.ui.currency

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import iyotetsuya.currencyconversion.repository.CurrencyRepository
import iyotetsuya.currencyconversion.util.TestUtil
import iyotetsuya.currencyconversion.util.mock
import iyotetsuya.currencyconversion.vo.CurrencyRate
import iyotetsuya.currencyconversion.vo.Resource
import iyotetsuya.currencyconversion.vo.SupportedCurrency
import org.hamcrest.CoreMatchers.notNullValue
import org.hamcrest.MatcherAssert.assertThat
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import org.mockito.Mockito.*

@RunWith(JUnit4::class)
class CalculatorViewModelTest {

    @Rule
    @JvmField
    val instantExecutorRule = InstantTaskExecutorRule()

    private val repository = mock(CurrencyRepository::class.java)
    private var viewModel = CalculatorViewModel(repository)


    @Test
    fun testNull() {
//        assertThat(viewModel.supportedCurrencies, notNullValue())
//        assertThat(viewModel.currencyRateResource, notNullValue())
//        assertThat(viewModel.input, notNullValue())
//        verify(repository, never()).getCurrencyRateList("code")
//        verify(repository, never()).getCurrencies()
    }

    @Test
    fun noFetchWithoutObservers() {
        viewModel.init()
        viewModel.onCurrencySelected(SupportedCurrency("", ""))
        verify(repository, never()).getCurrencyRateList(anyString())
        verify(repository, never()).getCurrencies()
    }

    @Test
    fun fetchWhenObserved() {
        viewModel.supportedCurrencies.observeForever(mock())
        viewModel.currencyRateResource.observeForever(mock())
        viewModel.init()
        viewModel.onCurrencySelected(SupportedCurrency("Code", ""))
        verify(repository).getCurrencies()
        verify(repository).getCurrencyRateList("Code")
    }

    @Test
    fun changeWhileObserved() {
        viewModel.currencyRateResource.observeForever(mock())

        viewModel.onCurrencySelected(SupportedCurrency("NTD", ""))
        viewModel.onCurrencySelected(SupportedCurrency("USD", ""))

        verify(repository).getCurrencyRateList("NTD")
        verify(repository).getCurrencyRateList("USD")
    }

    @Test
    fun supportCurrency() {
        val observer = mock<Observer<Resource<List<SupportedCurrency>>>>()
        viewModel.supportedCurrencies.observeForever(observer)
        verifyNoMoreInteractions(observer)
        verifyNoMoreInteractions(repository)
        viewModel.init()
        verify(repository).getCurrencies()
    }

    @Test
    fun currencyRate() {
        val observer = mock<Observer<Resource<List<CurrencyRate>>>>()
        viewModel.currencyRateResource.observeForever(observer)
        verifyNoMoreInteractions(observer)
        verifyNoMoreInteractions(repository)
        viewModel.onCurrencySelected(SupportedCurrency("USD", ""))
        verify(repository).getCurrencyRateList("USD")
    }


    @Test
    fun retry() {
        viewModel.retry()
        verifyNoMoreInteractions(repository)
        viewModel.onCurrencySelected(SupportedCurrency("USD", ""))
        verifyNoMoreInteractions(repository)
        val observer = mock<Observer<Resource<List<CurrencyRate>>>>()
        viewModel.currencyRateResource.observeForever(observer)
        verify(repository).getCurrencyRateList("USD")
        reset(repository)
        viewModel.retry()
        verify(repository).getCurrencyRateList("USD")
    }

    @Test
    fun testSupportedCurrencyUI() {
        val supportedCurrency = MutableLiveData<Resource<List<SupportedCurrency>>>()
        `when`(repository.getCurrencies()).thenReturn(supportedCurrency)
        val observer = mock<Observer<Resource<List<SupportedCurrency>>>>()
        viewModel.supportedCurrencies.observeForever(observer)
        viewModel.init()
        val supportedCurrencyList = TestUtil.createSupportedCurrencyList(10, "code", "name")
        val value = Resource.success(supportedCurrencyList)
        supportedCurrency.value = value
        verify(observer).onChanged(value)
    }

}