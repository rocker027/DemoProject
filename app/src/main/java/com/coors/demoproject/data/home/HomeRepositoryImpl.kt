package com.coors.demoproject.data.home

import javax.inject.Inject

class HomeRepositoryImpl @Inject constructor() : HomeRepository {
    override fun getMenu(): List<HomeMenu> = listOf(
        HomeMenu(name = "Demo compose ver.", description = "test compose" ),
        HomeMenu(name = "CRO demo kotlin ver.", description = "cro demo test with kotlin"),
        HomeMenu(name = "CRO demo compose ver.", description = "cro demo test with compose")
    )
}