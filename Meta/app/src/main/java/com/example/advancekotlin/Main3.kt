package com.example.advancekotlin

fun main(args:Array<String>){
    val order = Order(500)
    val finalAmount = Order.afterTaxAmount(order.amountBeforeTax)
    println(finalAmount)
}