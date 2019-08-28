package iyotetsuya.currencyconversion.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import iyotetsuya.currencyconversion.vo.Currency

@Database(entities = [Currency::class], version = 1, exportSchema = false)
abstract class AppDb : RoomDatabase() {

    abstract fun currencyDao(): CurrencyDao

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
