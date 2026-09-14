package com.example.prueba3

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import android.widget.EditText
import android.widget.TextView
import android.widget.Button


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
        val txtNum1 = findViewById<EditText>(R.id.txtNum1);
        val txtNum2 = findViewById<EditText>(R.id.txtNum2);
        val txtResultado = findViewById<TextView>(R.id.txtResultado)
        val btnCalculo = findViewById<Button>(R.id.btnCalculo)
        btnCalculo.setOnClickListener{
            val num1 = txtNum1.text.toString().toInt()
            val num2 = txtNum2.text.toString().toInt()
            val suma = num1 + num2;
            txtResultado.text = "Resultado: $suma"
        }
    }
}