package iyotetsuya.currencyconversion.api

import androidx.lifecycle.LiveData
import retrofit2.http.GET

const val HTTPS_API_HOST = "http://apilayer.net/api/"

interface CurrencyLayerService {
    @GET("list")
    fun getSupportedCurrencies(): LiveData<ApiResponse<CurrenciesResponse>>
}