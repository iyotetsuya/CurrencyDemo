package iyotetsuya.currencyconversion.vo

import androidx.room.Entity
import com.google.gson.annotations.SerializedName

@Entity(primaryKeys = ["source", "target"])
data class CurrencyRate(
    @field:SerializedName("source")
    val source: String,
    @field:SerializedName("target")
    val target: String,
    @field:SerializedName("rate")
    val rate: Float

)