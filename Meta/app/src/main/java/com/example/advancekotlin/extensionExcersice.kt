package com.example.advancekotlin

class Dish(

    val dishName: String,

    val ingredients: MutableList<String>

)

fun Dish.printIngredients(){
    println(ingredients)
}

fun Dish.removeSalt(){
    ingredients.remove("Salt")

}

fun main(){
    val dishIng = mutableListOf<String>("Onion","Cheese","Water","Salt")
    var dish1 = Dish("Onion Soup", dishIng)
    dish1.printIngredients()
    dish1.removeSalt()
    dish1.printIngredients()
}