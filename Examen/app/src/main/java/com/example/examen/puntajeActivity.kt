package com.example.examen

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class puntajeActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_puntaje)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        val txtPuntaje = findViewById<TextView>(R.id.txtPuntaje)
        val txtJugador = findViewById<TextView>(R.id.txtJugador)
        val btnSalir = findViewById<Button>(R.id.btnSalir)
        val btnVolver = findViewById<Button>(R.id.btnVolver)


        val nombre = intent.getStringExtra("nombre")?: "Jugador"

        val preferencias = getSharedPreferences("puntajes", MODE_PRIVATE)
        val puntaje = preferencias.getInt(nombre,0)

        txtPuntaje.text = "Tu puntaje es de $puntaje"
        txtJugador.text = nombre

        btnVolver.setOnClickListener{
            val intento = Intent(this, adivinanzaActivity::class.java)
            intento.putExtra("nombre", nombre)
            intento.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
            startActivity(intento)
            finish()
        }

        btnSalir.setOnClickListener {
            val intento = Intent(this, MainActivity::class.java)
            intento.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
            startActivity(intento)
            finish()
        }
    }
}