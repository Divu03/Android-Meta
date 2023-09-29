package com.littlelemon.littlelemoningredients

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView

class Ingredient : AppCompatActivity() {
    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ingredient)

        when (intent.getStringExtra(MainActivity.DISH_NAME_KEY)) {
            "Hamburger" -> { findViewById<TextView>(R.id.ingredients_list).text = " Minced meat \nBun \nTomato" }
            "Pasta" -> { findViewById<TextView>(R.id.ingredients_list).text = "Spaghetti \nTomato \nParmesan" }
        }
    }
}