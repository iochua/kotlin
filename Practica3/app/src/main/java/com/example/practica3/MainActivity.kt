package com.example.practica3

import android.os.Bundle
import android.widget.Button
import android.widget.CheckBox
import android.widget.EditText
import android.widget.TextView
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
        val txtResultadoSuma = findViewById<TextView>(R.id.txtResultadoSuma)
        val txtResultadoResta = findViewById<TextView>(R.id.txtResultadoResta)
        val btnSuma = findViewById<CheckBox>(R.id.btnSuma)
        val btnResta = findViewById<CheckBox>(R.id.btnResta)
        val btnCalculo = findViewById<Button>(R.id.btnCalculo)

        btnCalculo.setOnClickListener{
            if(btnSuma.isChecked){
                txtResultadoSuma.text="Resultado Suma: " + (txtNum1.text.toString().toInt() + txtNum2.text.toString().toInt())
            }
            if(btnResta.isChecked){
                txtResultadoResta.text="Resultado Resta: " + (txtNum1.text.toString().toInt() - txtNum2.text.toString().toInt())
            }
            if(!(btnSuma.isChecked)){
                txtResultadoSuma.text="---"
            }
            if(!(btnResta.isChecked)){
                txtResultadoResta.text="---"
            }
        }



    }
}