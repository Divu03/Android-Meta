package com.example.advancekotlin

import org.junit.Test
import org.junit.Assert.*

class ProductTest{
    private  val classUnderTest = Product("Spaghetti",20.00,3)
    private val product2 = Product("Steak",30.00,8)
    private val product3 = Product("Lasagna",10.00,0)

    @Test
    fun `applyDiscount validate`(){
        val res = classUnderTest.applyDiscount(20)
        assertEquals(16.00,res,0.0)
        val res2 = product2.applyDiscount(0)
        val res3 = product3.applyDiscount(0)
        assertEquals(30.00,res2,0.0)
        assertEquals(10.00,res3,0.0)
    }
}