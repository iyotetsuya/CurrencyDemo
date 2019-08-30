package iyotetsuya.currencyconversion.db

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import iyotetsuya.currencyconversion.util.LiveDataTestUtil.getValue
import iyotetsuya.currencyconversion.util.TestUtil
import org.hamcrest.CoreMatchers
import org.hamcrest.MatcherAssert
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class CurrencyRateDaoTest : AppDbTest() {
    @Rule
    @JvmField
    val rule = InstantTaskExecutorRule()

    @Test
    fun insertAndRead() {
        val source = "USD"
        val target = listOf("NTD", "JPY")
        val currencyRatesList = TestUtil.createCurrencyRateList(source, target, 0f)
        db.currencyRateDao().insertCurrencyRate(currencyRatesList)
        val loaded = getValue(db.currencyRateDao().getCurrencyRateList("USD"))
        MatcherAssert.assertThat(loaded, CoreMatchers.notNullValue())
        MatcherAssert.assertThat(loaded[0].source, CoreMatchers.`is`("USD"))
        MatcherAssert.assertThat(loaded[0].target, CoreMatchers.`is`("JPY"))
        MatcherAssert.assertThat(loaded[1].source, CoreMatchers.`is`("USD"))
        MatcherAssert.assertThat(loaded[1].target, CoreMatchers.`is`("NTD"))
    }
}