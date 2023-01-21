package com.coors.demoproject.data.demo

import com.coors.commoncore.model.AnchorModel
import com.coors.commoncore.model.BaseResponse
import com.coors.commoncore.network.api.DemoApiService
import com.coors.commoncore.result.Result
import javax.inject.Inject

class DemoRepositoryImpl @Inject constructor(
    private val demoApiService: DemoApiService
) : DemoRepository {
    override suspend fun getAnchors(): Result<BaseResponse<List<AnchorModel>>> = demoApiService.getAnchors()
}