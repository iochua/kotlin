package com.example.practica4

import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import android.widget.Button
import android.widget.EditText


class toastActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_toast)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        val txtClave = findViewById<EditText>(R.id.txtClave)
        val btnVerificar = findViewById<Button>(R.id.btnVerificar)
        val btnSalir = findViewById<Button>(R.id.btnSalir)

        btnVerificar.setOnClickListener{
            val claveIngresada = txtClave.text.toString()
            if(claveIngresada == "123456"){
                Toast.makeText(this,"Bienvenido",Toast.LENGTH_SHORT).show()
            }else{
                Toast.makeText(this,"CLave Incorrecta",Toast.LENGTH_SHORT).show()

            }
        }
    }
}