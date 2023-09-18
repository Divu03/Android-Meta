package com.example.advancekotlin

class Waiter: Personnel {

    val id : Int = -1
    val name : String = ""
    override fun serveCustomer() {
        TODO("Not yet implemented")
    }
    companion object{
        var branchName : String = ""
        var branchAddress : String = ""
    }
}