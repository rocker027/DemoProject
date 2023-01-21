package com.coors.demoproject.data.demo

import com.coors.commoncore.model.AnchorModel
import com.coors.commoncore.model.BaseResponse
import com.coors.commoncore.result.Result

interface DemoRepository {
    suspend fun getAnchors(): Result<BaseResponse<List<AnchorModel>>>
}