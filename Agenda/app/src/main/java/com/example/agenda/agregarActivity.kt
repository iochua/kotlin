package com.example.agenda

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class agregarActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_agregar)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        val txtNombre = findViewById<EditText>(R.id.txtNombre)
        val txtNumero = findViewById<EditText>(R.id.txtNumero)
        val btnGuardar = findViewById<Button>(R.id.btnGuardar)
        val btnSalir = findViewById<Button>(R.id.btnSalir)

        btnGuardar.setOnClickListener {

        }

        btnSalir.setOnClickListener {
            finish()
        }
    }
}