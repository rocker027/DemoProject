package com.coors.demoproject.data.demo

import com.coors.commoncore.network.api.DemoApiService

class DemoRepositoryImpl constructor(
    private val demoApiService: DemoApiService
) : DemoRepository {
    override suspend fun getUsers(): List<String> = demoApiService.getUsers()
}