package com.example.advancekotlin

object Table {
    val customers : MutableList<String> = mutableListOf()
    fun addCustomer(customer : String){
        customers.add(customer)
    }
    fun removeCustomer(customer: String){
        customers.remove(customer)
    }
}