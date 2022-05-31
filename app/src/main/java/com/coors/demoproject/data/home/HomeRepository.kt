package com.coors.demoproject.data.home

interface HomeRepository {
    fun getMenu(): List<HomeMenu>
}
