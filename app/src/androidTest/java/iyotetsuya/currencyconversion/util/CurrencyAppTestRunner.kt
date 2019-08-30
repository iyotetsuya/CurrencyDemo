package iyotetsuya.currencyconversion.util

import android.app.Application
import android.content.Context
import androidx.test.runner.AndroidJUnitRunner

import iyotetsuya.currencyconversion.CurrencyApp

class CurrencyAppTestRunner : AndroidJUnitRunner() {
    override fun newApplication(cl: ClassLoader, className: String, context: Context): Application {
        return super.newApplication(cl, CurrencyApp::class.java.name, context)
    }
}
