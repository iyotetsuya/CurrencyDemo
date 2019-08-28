package iyotetsuya.currencyconversion.db

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import iyotetsuya.currencyconversion.vo.Currency

@Dao
abstract class CurrencyDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract fun insertCurrencies(currencies: List<Currency>)

    @Query("SELECT * FROM Currency")
    abstract fun getCurrencies(): LiveData<List<Currency>>
}


