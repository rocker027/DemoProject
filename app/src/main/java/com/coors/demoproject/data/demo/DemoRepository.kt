package com.coors.demoproject.data.demo

import com.coors.commoncore.model.AnchorModel

interface DemoRepository {
    suspend fun getAnchors(): List<AnchorModel>
}