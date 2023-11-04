package com.example.advancekotlin

class Parent{
    var count : Int = 4
}

fun Parent.efunc(countAdd : Int){
    this.count += countAdd
}

fun main() {
    val p = Parent()
    println(p.count)
    p.efunc(5)
    println(p.count)
}