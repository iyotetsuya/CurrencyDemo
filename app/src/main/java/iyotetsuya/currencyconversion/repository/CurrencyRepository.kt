package iyotetsuya.currencyconversion.repository

import androidx.lifecycle.LiveData
import iyotetsuya.currencyconversion.AppExecutors
import iyotetsuya.currencyconversion.api.CurrenciesResponse
import iyotetsuya.currencyconversion.api.CurrencyLayerService
import iyotetsuya.currencyconversion.api.QuotesResponse
import iyotetsuya.currencyconversion.db.CurrencyDao
import iyotetsuya.currencyconversion.db.CurrencyRateDao
import iyotetsuya.currencyconversion.util.RateLimiter
import iyotetsuya.currencyconversion.vo.Currency
import iyotetsuya.currencyconversion.vo.CurrencyRate
import iyotetsuya.currencyconversion.vo.Resource
import java.util.concurrent.TimeUnit
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class CurrencyRepository @Inject constructor(
    private val currencyDao: CurrencyDao,
    private val currencyRateDao: CurrencyRateDao,
    private val appExecutors: AppExecutors,
    private val service: CurrencyLayerService
) {
    companion object {
        const val CURRENCIES_KEY = "currencies"
    }

    private val rateLimiter = RateLimiter<String>(30, TimeUnit.MINUTES)

    fun getCurrencies(): LiveData<Resource<List<Currency>>> {
        return object : NetworkBoundResource<List<Currency>, CurrenciesResponse>(appExecutors) {
            override fun saveCallResult(item: CurrenciesResponse) {
                item.currencies?.let {
                    val result = item.currencies.toList().map { e -> Currency(e.first, e.second) }
                    currencyDao.insertCurrencies(result)
                }
            }

            override fun shouldFetch(data: List<Currency>?): Boolean {
                return data == null || data.isEmpty() || rateLimiter.shouldFetch(CURRENCIES_KEY)
            }

            override fun loadFromDb() = currencyDao.getCurrencies()

            override fun createCall() = service.getSupportedCurrencies()

            override fun onFetchFailed() {
                rateLimiter.reset(CURRENCIES_KEY)
            }
        }.asLiveData()
    }

    fun getCurrencyRateList(currencyCode: String): LiveData<Resource<List<CurrencyRate>>> {
        return object : NetworkBoundResource<List<CurrencyRate>, QuotesResponse>(appExecutors) {
            override fun saveCallResult(item: QuotesResponse) {
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

            override fun createCall() = service.getQuotes(currencyCode)

            override fun onFetchFailed() {
                rateLimiter.reset(currencyCode)
            }
        }.asLiveData()
    }
}