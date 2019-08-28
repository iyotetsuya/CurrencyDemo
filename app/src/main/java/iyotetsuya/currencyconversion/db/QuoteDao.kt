package iyotetsuya.currencyconversion.db

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import iyotetsuya.currencyconversion.vo.Quote

@Dao
abstract class QuoteDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract fun insertQuotes(Quotes: List<Quote>)

    @Query("SELECT * FROM Quote")
    abstract fun getQuotes(): LiveData<List<Quote>>
}

