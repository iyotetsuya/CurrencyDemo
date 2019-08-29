package iyotetsuya.currencyconversion.db

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import iyotetsuya.currencyconversion.vo.SupportedCurrency

@Dao
abstract class SupportedCurrencyDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract fun insertCurrencies(supportedCurrencies: List<SupportedCurrency>)

    @Query("SELECT * FROM SupportedCurrency")
    abstract fun getCurrencies(): LiveData<List<SupportedCurrency>>
}


