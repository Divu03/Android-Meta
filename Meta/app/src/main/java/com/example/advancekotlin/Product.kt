package com.example.advancekotlin

data class Product(
    val title: String,
    var price: Double,
    var amount: Int
) {
    fun applyDiscount(discountPercent: Int): Double {
        if (amount in 1..5) {
            price -= (price * discountPercent / 100)
        }
        return price
    }

}