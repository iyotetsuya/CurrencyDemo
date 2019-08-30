package iyotetsuya.currencyconversion.db

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import iyotetsuya.currencyconversion.util.LiveDataTestUtil
import iyotetsuya.currencyconversion.util.TestUtil
import org.hamcrest.CoreMatchers
import org.hamcrest.MatcherAssert
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class SupportedCurrencyDaoTest : AppDbTest() {
    @Rule
    @JvmField
    val rule = InstantTaskExecutorRule()

    @Test
    fun insertAndRead() {

        val supportedCurrencyList = TestUtil.createSupportedCurrencyList(2, "code", "name")
        db.supportedCurrencyDao().insertCurrencies(supportedCurrencyList)
        val loaded = LiveDataTestUtil.getValue(db.supportedCurrencyDao().getCurrencies())
        MatcherAssert.assertThat(loaded, CoreMatchers.notNullValue())
        MatcherAssert.assertThat(loaded[0].code, CoreMatchers.`is`("code0"))
        MatcherAssert.assertThat(loaded[0].name, CoreMatchers.`is`("name0"))
        MatcherAssert.assertThat(loaded[1].code, CoreMatchers.`is`("code1"))
        MatcherAssert.assertThat(loaded[1].name, CoreMatchers.`is`("name1"))
    }
}

