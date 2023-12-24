package com.littlelemon.menu

import org.junit.Test
import org.junit.jupiter.api.Assertions.*

class FilterHelperTest{
    private val classUnderTest = FilterHelper()
    private val sampleProductsList = mutableListOf(
        ProductItem(title = "Black tea", price = 3.00, category = "Drinks", R.drawable.black_tea),
        ProductItem(title = "Croissant", price = 7.00, category = "Dessert", R.drawable.croissant),
        ProductItem(title = "Bouillabaisse", price = 20.00, category = "Food", R.drawable.bouillabaisse)
    )
    @Test
    fun filterProducts_filterTypeDessert_croissantReturned(){
        val res1 =  classUnderTest.filterProducts(FilterType.Dessert,sampleProductsList)
        assertEquals(res1, listOf(ProductItem(title = "Croissant", price = 7.00, category = "Dessert", R.drawable.croissant)))
    }
}