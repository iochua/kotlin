package com.example.guesser

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
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
        val txtNombre = findViewById<EditText>(R.id.txtNombre)
        val btnJugar = findViewById<Button>(R.id.btnJugar)

        btnJugar.setOnClickListener{
            val nombre = txtNombre.text.toString()
            if (nombre.isNotEmpty()){
                val preferencias = getSharedPreferences("puntajes",MODE_PRIVATE)
                if (!preferencias.contains(nombre)) {
                    preferencias.edit().putInt(nombre, 0).apply()
                    Toast.makeText(this,"Bienvenido $nombre", Toast.LENGTH_SHORT).show()
                }
                else{
                    val puntaje = preferencias.getInt(nombre,0)
                    Toast.makeText(this,"Hola $nombre, tu puntaje es: $puntaje", Toast.LENGTH_SHORT).show()
                }
                val intento = Intent(this, adivinanzaActivity::class.java)
                intento.putExtra("nombre", nombre)
                startActivity(intento)
            }
            else{
                Toast.makeText(this,"Ingresa tu nombre para jugar", Toast.LENGTH_SHORT).show()
            }
        }
    }
}