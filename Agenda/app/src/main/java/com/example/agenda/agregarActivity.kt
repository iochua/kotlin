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
        val txtEmail = findViewById<EditText>(R.id.txtEmail)
        val txtDireccion = findViewById<EditText>(R.id.txtDireccion)
        val txtNotas = findViewById<EditText>(R.id.txtNotas)
        val btnGuardar = findViewById<Button>(R.id.btnGuardar)
        val btnSalir = findViewById<Button>(R.id.btnSalir)

        btnGuardar.setOnClickListener {
            val admin = AdminSQLiteOpenHelper(this, "administracion", null, 1)
            val numero = txtNumero.text.toString().trim()
            val nombre = txtNombre.text.toString().trim()
            val email = txtEmail.text.toString().trim()
            val direccion = txtDireccion.text.toString().trim()
            val notas = txtNotas.text.toString().trim()

            if (nombre.isEmpty() || numero.isEmpty()) {
                Toast.makeText(this, "El nombre y/o número no pueden estar vacíos", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            if (numero.length < 7) {
                Toast.makeText(this, "El teléfono debe tener al menos 7 dígitos", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            //cambiar el nombre de estas variables
            val bdConsulta = admin.readableDatabase
            val fila = bdConsulta.rawQuery("SELECT numero FROM contactos WHERE numero='$numero'", null)

            if (fila.moveToFirst()) {
                Toast.makeText(this, "Ya existe un contacto con este número", Toast.LENGTH_SHORT).show()
                fila.close()
                bdConsulta.close()
                return@setOnClickListener
            }
            bdConsulta.close()
            fila.close()

            val bdEscritura = admin.writableDatabase
            val registro = ContentValues()
            registro.put("numero",numero)
            registro.put("nombre",nombre)
            if (email.isNotEmpty())
                registro.put("email",email)
            if (direccion.isNotEmpty())
                registro.put("direccion",direccion)
            if (notas.isNotEmpty())
                registro.put("notas",notas)


            bdEscritura.insert("contactos",null,registro)

            txtNumero.setText("")
            txtNombre.setText("")
            txtEmail.setText("")
            txtDireccion.setText("")
            txtNotas.setText("")

            Toast.makeText(this,"Contacto creado",Toast.LENGTH_SHORT).show()
            bdEscritura.close()
        }

        btnSalir.setOnClickListener {
            finish()
        }
    }
}