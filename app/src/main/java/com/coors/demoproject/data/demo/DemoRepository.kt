package com.coors.demoproject.data.demo

interface DemoRepository {
    suspend fun getUsers(): List<String>
}