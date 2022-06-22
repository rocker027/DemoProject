package com.coors.demoproject.data.home


sealed class HomeMenu(
    open val name: String,
    open val description: String
)

data class Home(
    override val name: String = "Home",
    override val description: String = "Home menu"
) : HomeMenu(name, description)

data class TestCompose(
    override val name: String = "Test Compose",
    override val description: String = "test compose"
) : HomeMenu(name, description)

data class CroDemoKotlin(
    override val name: String = "Cro Demo",
    override val description: String = "cro demo test with kotlin"
) : HomeMenu(name, description)

data class CroDemoCompose(
    override val name: String = "Cro Demo Compose",
    override val description: String = "cro demo test with compose"
) : HomeMenu(name, description)