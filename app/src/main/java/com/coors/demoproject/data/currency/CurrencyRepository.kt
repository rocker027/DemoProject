package com.coors.demoproject.data.currency

interface CurrencyRepository {

    fun fetchCurrencyList(): List<CurrencyInfo>
}