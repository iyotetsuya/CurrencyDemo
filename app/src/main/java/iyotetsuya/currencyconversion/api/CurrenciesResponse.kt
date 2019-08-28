package iyotetsuya.currencyconversion.api

import iyotetsuya.currencyconversion.vo.Currency

data class CurrenciesResponse(
    val success: Boolean?,
    val terms: String?,
    val privacy: String?,
    val currencies: List<Currency>?
)