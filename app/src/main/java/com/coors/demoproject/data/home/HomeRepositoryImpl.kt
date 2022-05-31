package com.coors.demoproject.data.home

import javax.inject.Inject

class HomeRepositoryImpl @Inject constructor() : HomeRepository {
    override fun getMenu(): List<HomeMenu> = listOf(
        HomeMenu("Demo compose ver."),
        HomeMenu("CRO demo kotlin ver."),
        HomeMenu("CRO demo compose ver.")
    )
}