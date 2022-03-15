package com.coors.demoproject.data.currency

import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.Moshi
import com.squareup.moshi.Types
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import javax.inject.Inject

class CurrencyInfoMapper @Inject constructor() : Mapper<String, List<CurrencyInfo>> {
    override suspend fun map(from: String): List<CurrencyInfo> {
        val moshi = Moshi.Builder()
            .add(KotlinJsonAdapterFactory())
            .build()
        val listType = Types.newParameterizedType(List::class.java, CurrencyInfo::class.java)
        val adapter: JsonAdapter<List<CurrencyInfo>> = moshi.adapter(listType)
        val mapperResult = adapter.fromJson(from)
        return mapperResult ?: emptyList()
    }
}