package com.coors.demoproject

import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.Test

class BuyBookTest {
    @Test
    fun buyOneBook(){
        val book = Book("Harry Potter")
        val cart = Cart()
        cart.addBook(book)
        cart.totalPrice().shouldBe(100)
    }

    @Test
    fun buyTwoSameBook(){
        val book = Book("Harry Potter")
        val cart = Cart()
        cart.addBook(book)
        cart.addBook(book)
        cart.totalPrice().shouldBe(200)
    }
}