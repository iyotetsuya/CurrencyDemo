package iyotetsuya.currencyconversion.repository

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import iyotetsuya.currencyconversion.api.CurrencyLayerService
import iyotetsuya.currencyconversion.api.ListResponse
import iyotetsuya.currencyconversion.api.LiveResponse
import iyotetsuya.currencyconversion.db.AppDb
import iyotetsuya.currencyconversion.db.CurrencyRateDao
import iyotetsuya.currencyconversion.db.SupportedCurrencyDao
import iyotetsuya.currencyconversion.util.ApiUtil.successCall
import iyotetsuya.currencyconversion.util.InstantAppExecutors
import iyotetsuya.currencyconversion.util.TestUtil
import iyotetsuya.currencyconversion.util.mock
import iyotetsuya.currencyconversion.vo.CurrencyRate
import iyotetsuya.currencyconversion.vo.Resource
import iyotetsuya.currencyconversion.vo.SupportedCurrency
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import org.mockito.ArgumentMatchers
import org.mockito.Mockito.*

@RunWith(JUnit4::class)
class CurrencyRepositoryTest {
    @Rule
    @JvmField
    val instantExecutorRule = InstantTaskExecutorRule()

    private lateinit var repository: CurrencyRepository
    private val supportedCurrencyDao = mock(SupportedCurrencyDao::class.java)
    private val currencyRateDao = mock(CurrencyRateDao::class.java)
    private val service = mock(CurrencyLayerService::class.java)

    @Before
    fun init() {
        val db = mock(AppDb::class.java)
        `when`(db.currencyRateDao()).thenReturn(currencyRateDao)
        `when`(db.supportedCurrencyDao()).thenReturn(supportedCurrencyDao)
        `when`(db.runInTransaction(ArgumentMatchers.any())).thenCallRealMethod()
        repository = CurrencyRepository(
            supportedCurrencyDao,
            currencyRateDao,
            InstantAppExecutors(),
            service
        )
    }

    @Test
    fun getCurrencies() {
        val dbData = MutableLiveData<List<SupportedCurrency>>()
        `when`(supportedCurrencyDao.getCurrencies()).thenReturn(dbData)
        val supportedCurrencyList = TestUtil.createSupportedCurrencyList(10, "code", "name")
        val map = supportedCurrencyList.map { it.code to it.name }.toMap()
        val listResponse = ListResponse(true, null, null, null, map)

        val call = successCall(listResponse)
        `when`(service.getList()).thenReturn(call)

        val data = repository.getCurrencies()
        verify(supportedCurrencyDao).getCurrencies()
        verifyNoMoreInteractions(service)
        val observer = mock<Observer<Resource<List<SupportedCurrency>>>>()
        data.observeForever(observer)
        verifyNoMoreInteractions(service)
        verify(observer).onChanged(Resource.loading(null))
        val updatedDbData = MutableLiveData<List<SupportedCurrency>>()
        `when`(supportedCurrencyDao.getCurrencies()).thenReturn(updatedDbData)

        dbData.postValue(null)
        verify(service).getList()
        verify(supportedCurrencyDao).insertCurrencies(supportedCurrencyList)

        updatedDbData.postValue(supportedCurrencyList)
        verify(observer).onChanged(Resource.success(supportedCurrencyList))
    }

    @Test
    fun getCurrencyRateList() {
        val source = "USD"
        val target = listOf("NTD", "JPY", "NZD", "OMR", "PAB")
        val dbData = MutableLiveData<List<CurrencyRate>>()
        `when`(currencyRateDao.getCurrencyRateList(source)).thenReturn(dbData)
        val currencyRatesList = TestUtil.createCurrencyRateList(source, target, 0f)
        val map = currencyRatesList.map { it.source + it.target to it.rate }.toMap()
        val liveResponse = LiveResponse(true, null, null, null, null, null, null, null, map)

        val call = successCall(liveResponse)
        `when`(service.getLive(source)).thenReturn(call)

        val data = repository.getCurrencyRateList(source)
        verify(currencyRateDao).getCurrencyRateList(source)
        verifyNoMoreInteractions(service)
        val observer = mock<Observer<Resource<List<CurrencyRate>>>>()
        data.observeForever(observer)
        verifyNoMoreInteractions(service)
        verify(observer).onChanged(Resource.loading(null))
        val updatedDbData = MutableLiveData<List<CurrencyRate>>()
        `when`(currencyRateDao.getCurrencyRateList(source)).thenReturn(updatedDbData)

        dbData.postValue(null)
        verify(service).getLive(source)
        verify(currencyRateDao).insertCurrencyRate(currencyRatesList)

        updatedDbData.postValue(currencyRatesList)
        verify(observer).onChanged(Resource.success(currencyRatesList))
    }
}