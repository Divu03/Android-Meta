package com.littlelemon.littlelemoningredients

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        findViewById<Button>(R.id.main_dish_1).setOnClickListener {

            startActivity(start(this, "Hamburger"))
        }
        findViewById<Button>(R.id.main_dish_2).setOnClickListener {

            startActivity(start(this, "Pasta"))
        }
    }

    companion object{
        var DISH_NAME_KEY = "DishName"
        fun start(context: Context, dishName: String): Intent {
            val intent = Intent(context,Ingredient::class.java)
            intent.putExtra(DISH_NAME_KEY,dishName)
            return intent
        }

    }
}


