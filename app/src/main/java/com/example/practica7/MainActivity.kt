package com.example.practica7

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        val btnSupabase = findViewById<Button>(R.id.btnSupabase)
        val btnGemini = findViewById<Button>(R.id.btnGemini)

        btnSupabase.setOnClickListener {
            val intento = Intent(this, activitySupabase::class.java)
            startActivity(intento)
        }
        btnGemini.setOnClickListener {
            val intento = Intent(this, activityGemini::class.java)
            startActivity(intento)
        }
    }
}