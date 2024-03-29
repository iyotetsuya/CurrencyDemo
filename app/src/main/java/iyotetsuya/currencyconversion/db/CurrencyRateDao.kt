package iyotetsuya.currencyconversion.db

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import iyotetsuya.currencyconversion.vo.CurrencyRate

@Dao
abstract class CurrencyRateDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract fun insertCurrencyRate(currencyRates: List<CurrencyRate>)

    @Query("SELECT * FROM CurrencyRate WHERE source = :source")
    abstract fun getCurrencyRateList(source: String): LiveData<List<CurrencyRate>>
}

