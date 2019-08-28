package iyotetsuya.currencyconversion.api

abstract class BaseResponse {
    abstract val success: Boolean
    abstract val error: Error?
}

