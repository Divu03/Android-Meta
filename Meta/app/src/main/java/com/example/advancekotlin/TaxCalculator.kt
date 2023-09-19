package com.example.advancekotlin

class TaxCalculator {
    companion object{
        var salesTaxPercentage : Double = 15.0
        fun getTaxAmountForOrderItems(orderItemList: List<OrderItem>):Double{
            var orderAmount: Double = 0.00
            for (i in orderItemList){
                orderAmount += i.price
            }
            var salesTax = orderAmount * salesTaxPercentage / 100
            return salesTax
        }
    }
}
class OrderItem(var name : String,var price : Double)
fun main(){

    var orderItemListP: List<OrderItem> = listOf(
        OrderItem("Burger", 8.00),
        OrderItem("Fries", 4.00),
        OrderItem("Soda", 2.00)
    )
    print(TaxCalculator.getTaxAmountForOrderItems(orderItemListP))
}
