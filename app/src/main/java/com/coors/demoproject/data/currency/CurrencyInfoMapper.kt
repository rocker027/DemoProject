package com.coors.demoproject.data.currency

import com.coors.commoncore.data.mapper.Mapper
import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.Moshi
import com.squareup.moshi.Types
import javax.inject.Inject

class CurrencyInfoMapper @Inject constructor(
    private val moshi: Moshi
) : Mapper<String, List<CurrencyInfo>> {
    override suspend fun map(from: String): List<CurrencyInfo> {
        val listType = Types.newParameterizedType(List::class.java, CurrencyInfo::class.java)
        val adapter: JsonAdapter<List<CurrencyInfo>> = moshi.adapter(listType)
        val mapperResult = adapter.fromJson(from)
        return mapperResult ?: emptyList()
    }
}