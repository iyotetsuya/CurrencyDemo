package iyotetsuya.currencyconversion.vo

import androidx.room.Entity
import com.google.gson.annotations.SerializedName

@Entity(primaryKeys = ["name"])
data class Quote(
    @field:SerializedName("name")
    val name: String,
    @field:SerializedName("value")
    val value: Float
)