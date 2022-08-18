package com.coors.demoproject

import io.kotest.core.spec.style.StringSpec
import io.kotest.data.blocking.forAll
import io.kotest.data.row
import io.kotest.matchers.ints.shouldBeGreaterThan
import io.kotest.matchers.should
import io.kotest.matchers.shouldBe
import io.kotest.matchers.string.startWith

import org.junit.Assert.*
import org.junit.runner.RunWith
import kotlin.contracts.contract
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
    "test other ktx" {
        val value1 = ""
        val value2 = "null"
        val value3 = "null"

//        doOnNotNull(value1,value2,value3) {
//            value1 shouldBe null
//        }
        listOf<Int>(1,2,3).filterTo(mutableListOf(), { it > 2 }) shouldBe listOf(3)

        value1.isNotEmpty().doOnTrue {
            it shouldBe true
        }.doOnFalse {
            it shouldBe false
        }


    }

    "test fold ext" {
        (1..5).fold(1) { acc, i -> acc + i } shouldBe 15


        (1..5).reduce { acc, i -> acc + i } shouldBe 15

        // intype 1
        obtainTestInterface {

        }

        // type 2
        addAdapter(
            alpha = {},
            result = {},
            process = {}
        )

        fromJson("test",TestAdapter::class.java)

    }

})

fun doOnNotNull(vararg obj: String? , action: () -> Unit) {
    if (obj.filterNotNull().isNotEmpty())  {
        action()
    }
}

fun Boolean.doOnTrue(action: (Boolean) -> Unit): Boolean {
    if (this) {
        action(this)
    }
    return this
}

fun Boolean.doOnFalse(action: (Boolean) -> Unit): Boolean {
    if (!this) {
        action(this)
    }
    return this
}


interface TestInterface {
    fun alpha()
    fun result()
    fun process()
}

fun obtainTestInterface(block: TestAdapter.() -> Unit): TestInterface {
    return TestAdapter().apply { block }
}

class TestAdapter : TestInterface {
    private var alpha: (() -> Unit)? = null
    private var result: (() -> Unit)? = null
    private var process: (() -> Unit)? = null


    fun setTest(block:() -> Unit) {
        alpha = block
    }


    fun setResult(block: () -> Unit) {
        result = block
    }

    fun setProcess(block: () -> Unit) {
        process = block
    }

    override fun alpha() {
        alpha?.invoke()
    }

    override fun result() {
        result?.invoke()
    }

    override fun process() {
        process?.invoke()
    }
}

fun addAdapter(
    alpha: () -> Unit,
    result: () -> Unit,
    process: () -> Unit
) : TestInterface {
    return object : TestInterface {
        override fun alpha() {
            println("alpha")
        }

        override fun result() {
            println("result")
        }

        override fun process() {
            println("process")
        }
    }
}

fun <T> fromJson(json: String,klass: Class<T>): T {
    return klass.newInstance()
}
