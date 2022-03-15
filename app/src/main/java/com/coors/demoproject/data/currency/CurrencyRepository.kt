package com.coors.demoproject.data.currency

interface CurrencyRepository {

    fun fetchCurrencyListJson(): String
}