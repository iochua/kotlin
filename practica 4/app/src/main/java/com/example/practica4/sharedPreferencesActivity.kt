package com.example.practica4

import android.graphics.Color
import android.os.Bundle
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class sharedPreferencesActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_shared_preferences)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        val btnAzul = findViewById<Button>(R.id.btnAzul)
        val btnBlanco = findViewById<Button>(R.id.btnBlanco)
        val contenedor = findViewById<ConstraintLayout>(R.id.main)

        val preferencias = getSharedPreferences("preferencias", MODE_PRIVATE)
        val colorGuardado = preferencias.getInt("color_fondo", Color.WHITE)
        contenedor.setBackgroundColor(colorGuardado)

        btnAzul.setOnClickListener {
            contenedor.setBackgroundColor(Color.BLUE)
            preferencias.edit().putInt("color_fondo", Color.BLUE).apply()
        }
        btnBlanco.setOnClickListener {
            contenedor.setBackgroundColor(Color.WHITE)
            preferencias.edit().putInt("color_fondo", Color.WHITE).apply()
        }
    }
}