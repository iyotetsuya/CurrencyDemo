package iyotetsuya.currencyconversion.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import iyotetsuya.currencyconversion.vo.SupportedCurrency
import iyotetsuya.currencyconversion.vo.CurrencyRate

@Database(entities = [SupportedCurrency::class, CurrencyRate::class], version = 6, exportSchema = false)
abstract class AppDb : RoomDatabase() {

    abstract fun supportedCurrencyDao(): SupportedCurrencyDao

    abstract fun currencyRateDao(): CurrencyRateDao

    companion object {
        @Volatile
        private var instance: AppDb? = null

        fun getInstance(context: Context): AppDb {
            return instance ?: synchronized(this) {
                instance ?: buildDatabase(context).also { instance = it }
            }
        }

        private fun buildDatabase(context: Context): AppDb {
            return Room.databaseBuilder(context, AppDb::class.java, "app-db")

                .build()
        }
    }
}
