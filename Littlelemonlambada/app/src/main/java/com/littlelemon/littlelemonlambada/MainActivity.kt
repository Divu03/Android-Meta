package com.littlelemon.littlelemonlambada

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.Toast

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val saveDataB = findViewById<Button>(R.id.saveDataButton)
        saveDataB.setOnClickListener { fun onClick(v: View){
            Toast.makeText(this, "Data saved", Toast.LENGTH_SHORT).show()
        } }
    }
}