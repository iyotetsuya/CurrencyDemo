package iyotetsuya.currencyconversion.api

data class ListResponse(
    override val success: Boolean,
    override val error: Error?,

    val terms: String?,
    val privacy: String?,
    val currencies: Map<String, String>?
) : BaseResponse()