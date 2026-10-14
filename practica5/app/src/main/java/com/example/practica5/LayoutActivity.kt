package com.example.practica5

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class LayoutActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_layout)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        val btnLinear = findViewById<Button>(R.id.btnLinear)
        val btnTable = findViewById<Button>(R.id.btnTable)
        val btnFrame = findViewById<Button>(R.id.btnFrame)
        val btnScroll = findViewById<Button>(R.id.btnScroll)

        btnLinear.setOnClickListener{
            val intento = Intent(this, LinearActivity::class.java)
            startActivity(intento)
        }
        btnTable.setOnClickListener{
            val intento = Intent(this, TableActivity::class.java)
            startActivity(intento)
        }
        btnFrame.setOnClickListener{
            val intento = Intent(this, FrameActivity::class.java)
            startActivity(intento)
        }
        btnScroll.setOnClickListener{
            val intento = Intent(this, ScrollActivity::class.java)
            startActivity(intento)
        }
    }
}