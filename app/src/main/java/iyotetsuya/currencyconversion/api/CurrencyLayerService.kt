package iyotetsuya.currencyconversion.api

import androidx.lifecycle.LiveData
import retrofit2.http.GET
import retrofit2.http.Query

const val HTTPS_API_HOST = "http://api.currencylayer.com/"

interface CurrencyLayerService {
    @GET("list")
    fun getList(): LiveData<ApiResponse<ListResponse>>

    @GET("live")
    fun getLive(@Query("source") source: String): LiveData<ApiResponse<LiveResponse>>
}