package com.coors.demoproject

import io.kotest.core.spec.style.StringSpec
import io.kotest.data.blocking.forAll
import io.kotest.data.row
import io.kotest.matchers.ints.shouldBeGreaterThan
import io.kotest.matchers.should
import io.kotest.matchers.shouldBe
import io.kotest.matchers.string.startWith
import org.junit.Test

import org.junit.Assert.*
import org.junit.runner.RunWith
import kotlin.math.max

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class ExampleUnitTest : StringSpec({
    "length should return size of string" {
        "hello".length shouldBe 5
    }
    "startsWith should test for a prefix" {
        "world" should startWith("wolr")
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