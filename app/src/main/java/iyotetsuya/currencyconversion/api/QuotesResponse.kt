package iyotetsuya.currencyconversion.api

data class QuotesResponse(
    override val success: Boolean,
    override val error: Error?,
    val terms: String?,
    val privacy: String?,
    val historical: Boolean?,
    val date: String?,
    val timestamp: String?,
    val source: String?,
    val quotes: Map<String, Float>?
) : BaseResponse()