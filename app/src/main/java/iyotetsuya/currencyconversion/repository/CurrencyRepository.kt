package iyotetsuya.currencyconversion.repository

import androidx.lifecycle.LiveData
import iyotetsuya.currencyconversion.util.AppExecutors
import iyotetsuya.currencyconversion.api.CurrencyLayerService
import iyotetsuya.currencyconversion.api.ListResponse
import iyotetsuya.currencyconversion.api.LiveResponse
import iyotetsuya.currencyconversion.db.CurrencyRateDao
import iyotetsuya.currencyconversion.db.SupportedCurrencyDao
import iyotetsuya.currencyconversion.util.RateLimiter
import iyotetsuya.currencyconversion.vo.CurrencyRate
import iyotetsuya.currencyconversion.vo.Resource
import iyotetsuya.currencyconversion.vo.SupportedCurrency
import java.util.concurrent.TimeUnit
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class CurrencyRepository @Inject constructor(
    private val supportedCurrencyDao: SupportedCurrencyDao,
    private val currencyRateDao: CurrencyRateDao,
    private val appExecutors: AppExecutors,
    private val service: CurrencyLayerService
) {
    companion object {
        const val CURRENCIES_KEY = "currencies"
    }

    private val rateLimiter = RateLimiter<String>(30, TimeUnit.MINUTES)

    fun getCurrencies(): LiveData<Resource<List<SupportedCurrency>>> {
        return object : NetworkBoundResource<List<SupportedCurrency>, ListResponse>(appExecutors) {
            override fun saveCallResult(item: ListResponse) {
                item.currencies?.let {
                    val result =
                        item.currencies.toList().map { e -> SupportedCurrency(e.first, e.second) }
                    supportedCurrencyDao.insertCurrencies(result)
                }
            }

            override fun shouldFetch(data: List<SupportedCurrency>?): Boolean {
                return data == null || data.isEmpty() || rateLimiter.shouldFetch(CURRENCIES_KEY)
            }

            override fun loadFromDb() = supportedCurrencyDao.getCurrencies()

            override fun createCall() = service.getList()

            override fun onFetchFailed() {
                rateLimiter.reset(CURRENCIES_KEY)
            }
        }.asLiveData()
    }

    fun getCurrencyRateList(currencyCode: String): LiveData<Resource<List<CurrencyRate>>> {
        return object : NetworkBoundResource<List<CurrencyRate>, LiveResponse>(appExecutors) {
            override fun saveCallResult(item: LiveResponse) {
                item.quotes?.let {
                    val result = item.quotes.toList().map { e ->
                        CurrencyRate(
                            e.first.subSequence(0, 3).toString(),
                            e.first.subSequence(3, 6).toString(),
                            e.second
                        )
                    }
                    currencyRateDao.insertCurrencyRate(result)
                }
            }

            override fun shouldFetch(data: List<CurrencyRate>?): Boolean {
                return data == null || data.isEmpty() || rateLimiter.shouldFetch(currencyCode)
            }

            override fun loadFromDb() = currencyRateDao.getCurrencyRateList(currencyCode)

            override fun createCall() = service.getLive(currencyCode)

            override fun onFetchFailed() {
                rateLimiter.reset(currencyCode)
            }
        }.asLiveData()
    }
}