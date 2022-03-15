package com.coors.demoproject.data.currency

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

class CurrencyListWrapper : ArrayList<CurrencyInfo>()


@JsonClass(generateAdapter = true)
data class CurrencyInfo(
    @Json(name = "id")
    val id: String,
    @Json(name = "name")
    val name: String,
    @Json(name = "symbol")
    val symbol: String
)