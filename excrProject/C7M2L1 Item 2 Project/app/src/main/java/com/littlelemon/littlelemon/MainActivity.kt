package com.littlelemon.littlelemon

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {
    private val menuList: RecyclerView by lazy { findViewById(R.id.main_menu) }
    private val downloadButton: View by lazy { findViewById(R.id.main_download_button) }

    private val adapter = MenuAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        menuList.adapter = adapter
    }
}
