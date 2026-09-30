package com.example.guesser

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class adivinanzaActivity : AppCompatActivity() {
    private var numSecreto = 0
    private var intentosRestantes = 3
    private var nombreUsuario = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_adivinanza)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        nombreUsuario = intent.getStringExtra("nombre") ?: "Jugador"

        val txtNumero = findViewById<EditText>(R.id.txtNumero)
        val btnVerificar = findViewById<Button>(R.id.btnVerificar)

        numSecreto = (1..50).random()

        btnVerificar.setOnClickListener {
            val textoIngresado = txtNumero.text.toString()
            if (textoIngresado.isNotEmpty()) {
                val numero = textoIngresado.toIntOrNull()
                if (numero != null && numero in 1..50) {
                    if (numero == numSecreto) {
                        Toast.makeText(this, "Felicidades $nombreUsuario, adivinaste el numero", Toast.LENGTH_SHORT).show()
                        val preferencias = getSharedPreferences("puntajes", MODE_PRIVATE)
                        val puntaje = preferencias.getInt(nombreUsuario, 0)
                        preferencias.edit().putInt(nombreUsuario, puntaje + 1).apply()

                        val intento = Intent(this, puntajeActivity::class.java)
                        intento.putExtra("nombre", nombreUsuario)
                        startActivity(intento)
                        finish()
                    } else {
                        intentosRestantes--
                        Toast.makeText(this, "Intentos restantes: $intentosRestantes", Toast.LENGTH_SHORT).show()
                        if (intentosRestantes > 0) {
                            if (numero > numSecreto) {
                                Toast.makeText(this, "El numero secreto es menor", Toast.LENGTH_SHORT).show()
                            } else {
                                Toast.makeText(this, "El numero secreto es mayor", Toast.LENGTH_SHORT).show()
                            }
                            txtNumero.text.clear()
                        } else {
                            Toast.makeText(this, "Perdiste, el numero secreto era: $numSecreto", Toast.LENGTH_SHORT).show()
                            val intento = Intent(this, puntajeActivity::class.java)
                            intento.putExtra("nombre", nombreUsuario)
                            startActivity(intento)
                            finish()
                        }
                    }
                } else {
                    Toast.makeText(this, "Ingresa un numero valido entre 1 y 50", Toast.LENGTH_SHORT).show()
                }
            } else {
                Toast.makeText(this, "Escribe un numero", Toast.LENGTH_SHORT).show()
            }
        }
    }
}