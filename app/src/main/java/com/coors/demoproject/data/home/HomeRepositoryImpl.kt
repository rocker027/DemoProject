package com.coors.demoproject.data.home

import javax.inject.Inject

class HomeRepositoryImpl @Inject constructor() : HomeRepository {
    override fun getMenu(): List<HomeMenu> =
        listOf(
            Home(),
            TestCompose(),
            CroDemoKotlin(),
            CroDemoCompose()
        )
}