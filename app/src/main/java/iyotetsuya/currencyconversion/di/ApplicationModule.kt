package iyotetsuya.currencyconversion.di


import androidx.room.Room
import dagger.Module
import dagger.Provides
import iyotetsuya.currencyconversion.CurrencyApp
import iyotetsuya.currencyconversion.api.CurrencyLayerService
import iyotetsuya.currencyconversion.api.HTTPS_API_HOST
import iyotetsuya.currencyconversion.db.AppDb
import iyotetsuya.currencyconversion.db.CurrencyDao
import iyotetsuya.currencyconversion.util.LiveDataCallAdapterFactory
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton


@Module(includes = [ViewModelModule::class])
class ApplicationModule {
    @Singleton
    @Provides
    fun provideCurrencyLayerService(): CurrencyLayerService {
        val httpLogging = HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)
        val httpClientBuilder =
            OkHttpClient.Builder().addInterceptor(httpLogging).addInterceptor { chain ->
                var request = chain.request()
                val url = request.url().newBuilder().addQueryParameter("access_key", "4df841079ee22fd1c97a3179d62d3640").build()
                request = request.newBuilder().url(url).build()
                chain.proceed(request)
            }

        return Retrofit.Builder()
            .baseUrl(HTTPS_API_HOST)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(LiveDataCallAdapterFactory())
            .client(httpClientBuilder.build())
            .build()
            .create(CurrencyLayerService::class.java)
    }

    @Singleton
    @Provides
    fun provideDb(context: CurrencyApp): AppDb {
        return Room
            .databaseBuilder(context, AppDb::class.java, "app.db")
//            .fallbackToDestructiveMigration()
            .build()
    }

    @Singleton
    @Provides
    fun provideCurrencyDao(db: AppDb): CurrencyDao {
        return db.currencyDao()
    }
}