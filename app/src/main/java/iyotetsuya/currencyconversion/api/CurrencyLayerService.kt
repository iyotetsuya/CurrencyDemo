package iyotetsuya.currencyconversion.api

import androidx.lifecycle.LiveData
import retrofit2.http.GET


const val HTTPS_API_HOST = "https://apilayer.net/api/"
//?access_key=YOUR_ACCESS_KEY

interface CurrencyLayerService {
    @GET("list/")
    fun getSupportedCurrencies(): LiveData<ApiResponse<CurrenciesResponse>>
}