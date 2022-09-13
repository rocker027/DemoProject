package com.coors.demoproject

class Cart {

    private val items = mutableListOf<Book>()

    fun addBook(vararg book: Book) {
        book.forEach { items.add(it) }
    }

    fun totalPrice(): Int {
        return items.size * 100
    }
}
