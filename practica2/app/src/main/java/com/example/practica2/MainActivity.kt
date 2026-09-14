package com.example.practica2

import android.os.Bundle
import android.widget.RadioButton
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
        val txtNum1 = findViewById<EditText>(R.id.txtNum1)
        val txtNum2 = findViewById<EditText>(R.id.txtNum2)
        val txtResultado = findViewById<TextView>(R.id.txtResultado)
        val radioSuma = findViewById<RadioButton>(R.id.radioSuma)
        val radioResta = findViewById<RadioButton>(R.id.radioResta)
        val btnCalcular = findViewById<Button>(R.id.btnCalcular)
        btnCalcular.setOnClickListener{
            if(radioSuma.isChecked)
                txtResultado.text = "Resultado: " + (txtNum1.text.toString().toInt() + txtNum2.text.toString().toInt())
            if(radioResta.isChecked)
                txtResultado.text = "Resultado: " + (txtNum1.text.toString().toInt() - txtNum2.text.toString().toInt())
        }

    }
}