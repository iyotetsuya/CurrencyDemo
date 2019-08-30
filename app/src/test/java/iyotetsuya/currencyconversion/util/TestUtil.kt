/*
 * Copyright (C) 2017 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

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
