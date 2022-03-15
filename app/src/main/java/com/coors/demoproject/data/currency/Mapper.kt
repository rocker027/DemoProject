package com.coors.demoproject.data.currency

interface Mapper<F, T> {
    suspend fun map(from: F): T
}
