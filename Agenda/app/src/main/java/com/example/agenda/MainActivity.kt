package com.example.agenda

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
        val btnAgregar = findViewById<Button>(R.id.btnAgregar)
        val btnContactos = findViewById<Button>(R.id.btnContactos)

        btnAgregar.setOnClickListener {
            val intento = Intent(this, agregarActivity::class.java)
            startActivity(intento)
        }
        btnContactos.setOnClickListener {
            val intento = Intent(this, contactosActivity::class.java)
            startActivity(intento)
        }
    }
}