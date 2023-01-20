package com.coors.demoproject.data.demo

import com.coors.commoncore.model.AnchorModel
import com.coors.commoncore.network.api.DemoApiService
import javax.inject.Inject

class DemoRepositoryImpl @Inject constructor(
    private val demoApiService: DemoApiService
) : DemoRepository {
    override suspend fun getAnchors(): List<AnchorModel> = demoApiService.getAnchors()
}