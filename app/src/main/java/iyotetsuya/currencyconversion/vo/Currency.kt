package iyotetsuya.currencyconversion.vo

import androidx.room.Entity
import com.google.gson.annotations.SerializedName

@Entity(primaryKeys = ["code"])
data class Currency(
    @field:SerializedName("code")
    val code: String,
    @field:SerializedName("name")
    val name: String

) {
    override fun toString(): String = code
}