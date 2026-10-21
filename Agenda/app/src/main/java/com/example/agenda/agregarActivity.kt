package com.example.agenda

import android.content.ContentValues
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import kotlin.text.insert

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
            val admin = AdminSQLiteOpenHelper(this, "administracion", null, 1)
            val bd = admin.writableDatabase

            val registro = ContentValues()
            registro.put("numero",txtNumero.text.toString())
            registro.put("nombre",txtNombre.text.toString())

            bd.insert("contactos",null,registro)

            txtNumero.setText("")
            txtNombre.setText("")

            Toast.makeText(this,"Se guardaron los datos del articulo",Toast.LENGTH_SHORT).show()
            bd.close()
        }

        btnSalir.setOnClickListener {
            finish()
        }
    }
}