package com.coors.demoproject

import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.ext.junit.runners.AndroidJUnit4
import io.kotest.core.spec.style.FunSpec
import io.kotest.core.spec.style.StringSpec
import io.kotest.data.blocking.forAll
import io.kotest.data.row
import io.kotest.matchers.ints.shouldBeGreaterThan
import io.kotest.matchers.should
import io.kotest.matchers.shouldBe
import io.kotest.matchers.string.startWith

import org.junit.Test
import org.junit.runner.RunWith

import org.junit.Assert.*
import kotlin.math.max


class MyTests : StringSpec({
    "length should return size of string" {
        "hello".length shouldBe 5
    }
    "startsWith should test for a prefix" {
        "world" should startWith("wor")
    }
    "should be able to pass all add case" {
        forAll(
            row(3, 4, 7),
            row(0, 1, 2),
            row(0, 0, 1),
            row(0, -4, 3),
            row(-3, -4, 1),
        ) { x, y, max ->
            max shouldBeGreaterThan max(x,y)
        }
    }

})

class MyFuncTest : FunSpec({
    val totaller = ToTaller()

    listOf(
        1,
        2,
        3,
    ).forEach {
        test("$it should be a three letter name") {
            totaller.add(it).shouldBe(it)
        }
    }
})

class ToTaller() {
    private var sum: Int = 0
    fun add(value: Int): Int = sum + value
}