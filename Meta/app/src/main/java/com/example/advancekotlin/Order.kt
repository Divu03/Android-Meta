package com.example.advancekotlin

class Order(val amountBeforeTax: Int) {
        companion object{
            val TAX_PERCENTAGE = 5.0
            fun afterTaxAmount(orderAmount:Int):Double{
                return orderAmount + (orderAmount * TAX_PERCENTAGE / 100.0)
            }
        }
}