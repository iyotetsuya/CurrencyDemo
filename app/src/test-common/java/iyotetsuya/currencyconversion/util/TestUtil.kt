package iyotetsuya.currencyconversion.util

import iyotetsuya.currencyconversion.vo.CurrencyRate
import iyotetsuya.currencyconversion.vo.SupportedCurrency

object TestUtil {

    fun createSupportedCurrencyList(
        count: Int,
        code: String,
        name: String
    ): List<SupportedCurrency> {
        return (0 until count).map {
            createSupportedCurrency(
                code = code + it,
                name = name + it
            )
        }
    }

    private fun createSupportedCurrency(code: String, name: String) = SupportedCurrency(
        code = code,
        name = name
    )

    fun createCurrencyRateList(
        source: String,
        target: List<String>,
        rate: Float
    ): List<CurrencyRate> {
        return (target.indices).map {
            createCurrencyRate(
                source = source,
                target = target[it],
                rate = rate + it
            )
        }

    }

    private fun createCurrencyRate(source: String, target: String, rate: Float) = CurrencyRate(
        source = source,
        target = target,
        rate = rate
    )

}
