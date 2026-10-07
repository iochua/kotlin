package com.example.practica4

import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.ListView
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class listActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_list)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        val txtSeleccion = findViewById<TextView>(R.id.txtSeleccion)
        val listCarreras = findViewById<ListView>(R.id.ListCarreras)

        val carreras = arrayOf("Tecnologías", "Biotecnología", "Mecatrónica", "Logística",
            "Nanotecnología", "Biomédica", "Energía", "Animación", "Administración")
        val alumnos = arrayOf("100", "300", "200", "350", "104", "500", "800", "240", "573")

        val adapter1 = ArrayAdapter<String>(this, android.R.layout.simple_list_item_1,carreras)
        listCarreras.adapter = adapter1
        listCarreras.setOnItemClickListener{ parent, view, position, id ->
            txtSeleccion.text = "Alumnos: ${alumnos[position]}"

        }
    }
}