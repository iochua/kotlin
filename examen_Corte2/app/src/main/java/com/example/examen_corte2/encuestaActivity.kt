package com.example.examen_corte2

import android.content.ContentValues
import android.os.Bundle
import android.widget.Button
import android.widget.RadioButton
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class encuestaActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_encuesta)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        val btnP1R1 = findViewById<RadioButton>(R.id.btnP1R1)
        val btnP1R2 = findViewById<RadioButton>(R.id.btnP1R2)
        val btnP1R3 = findViewById<RadioButton>(R.id.btnP1R3)
        val btnP1R4 = findViewById<RadioButton>(R.id.btnP1R4)

        val btnP2R1 = findViewById<RadioButton>(R.id.btnP2R1)
        val btnP2R2 = findViewById<RadioButton>(R.id.btnP2R2)
        val btnP2R3 = findViewById<RadioButton>(R.id.btnP2R3)
        val btnP2R4 = findViewById<RadioButton>(R.id.btnP2R4)

        val btnP3R1 = findViewById<RadioButton>(R.id.btnP3R1)
        val btnP3R2 = findViewById<RadioButton>(R.id.btnP3R2)
        val btnP3R3 = findViewById<RadioButton>(R.id.btnP3R3)
        val btnP3R4 = findViewById<RadioButton>(R.id.btnP3R4)

        val btnP4R1 = findViewById<RadioButton>(R.id.btnP4R1)
        val btnP4R2 = findViewById<RadioButton>(R.id.btnP4R2)
        val btnP4R3 = findViewById<RadioButton>(R.id.btnP4R3)
        val btnP4R4 = findViewById<RadioButton>(R.id.btnP4R4)

        val btnP5R1 = findViewById<RadioButton>(R.id.btnP5R1)
        val btnP5R2 = findViewById<RadioButton>(R.id.btnP5R2)
        val btnP5R3 = findViewById<RadioButton>(R.id.btnP5R3)

        val btnAlta = findViewById<Button>(R.id.btnAlta)

        btnAlta.setOnClickListener {
            btnAlta.setOnClickListener {
                val resP1 = when {
                    btnP1R1.isChecked -> 1
                    btnP1R2.isChecked -> 2
                    btnP1R3.isChecked -> 3
                    btnP1R4.isChecked -> 4
                    else -> 0
                }

                val resP2 = when {
                    btnP2R1.isChecked -> 1
                    btnP2R2.isChecked -> 2
                    btnP2R3.isChecked -> 3
                    btnP2R4.isChecked -> 4
                    else -> 0
                }

                val resP3 = when {
                    btnP3R1.isChecked -> 1
                    btnP3R2.isChecked -> 2
                    btnP3R3.isChecked -> 3
                    btnP3R4.isChecked -> 4
                    else -> 0
                }

                val resP4 = when {
                    btnP4R1.isChecked -> 1
                    btnP4R2.isChecked -> 2
                    btnP4R3.isChecked -> 3
                    btnP4R4.isChecked -> 4
                    else -> 0
                }

                val resP5 = when {
                    btnP5R1.isChecked -> 1
                    btnP5R2.isChecked -> 2
                    btnP5R3.isChecked -> 3
                    else -> 0
                }

                if (resP1 == 0 || resP2 == 0 || resP3 == 0 || resP4 == 0 || resP5 == 0) {
                    Toast.makeText(this, "Por favor responde todas las preguntas", Toast.LENGTH_SHORT).show()
                    return@setOnClickListener
                }

                val admin = AdminSQLiteOpenHelper(this, "administracion", null, 1)
                val bd = admin.writableDatabase

                val registro = ContentValues().apply {
                    put("primera", resP1)
                    put("segunda", resP2)
                    put("tercera", resP3)
                    put("cuarta", resP4)
                    put("quinta", resP5)
                }

                val resultado = bd.insert("respuestas", null, registro)
                bd.close()

                if (resultado != -1L) {
                    Toast.makeText(this, "Encuesta registrada con éxito", Toast.LENGTH_SHORT).show()
                } else {
                    Toast.makeText(this, "Error al registrar la encuesta", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }
}