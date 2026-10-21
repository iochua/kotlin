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

class editarContactoActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_editar_contacto)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        val txtNombre = findViewById<EditText>(R.id.txtNombre2)
        val txtNumero = findViewById<EditText>(R.id.txtNumero2)
        val btnGuardar = findViewById<Button>(R.id.btnGuardar2)
        val btnEliminar = findViewById<Button>(R.id.btnEliminar)
        val btnSalir = findViewById<Button>(R.id.btnSalir2)
        var ogNombre = intent.getStringExtra("nombreContacto")
        var ogNumero = intent.getStringExtra("numeroContacto")

        txtNombre.setText(ogNombre)
        txtNumero.setText(ogNumero)

        btnGuardar.setOnClickListener {
            val admin = AdminSQLiteOpenHelper(this, "administracion", null, 1)
            val bd = admin.writableDatabase
            val newNombre = txtNombre.text.toString().trim()
            val newNumero = txtNumero.text.toString().trim()

            if (newNombre.isEmpty()) {
                Toast.makeText(this, "El nombre no puede estar vacío", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            // 2. Validación: Teléfono con solo dígitos y mínimo 7 caracteres
            // Usamos una expresión regular (Regex) para verificar que solo sean números del 0 al 9
            if (!newNumero.matches(Regex("^[0-9]{7,}$"))) {
                Toast.makeText(this, "El teléfono debe tener al menos 7 dígitos y sin letras", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            // 3. Validación: No duplicados
            val admin = AdminSQLiteOpenHelper(this, "administracion", null, 1)

            // Validar si es un NUEVO registro o si cambió de número en la edición
            // (Si estás en la pantalla de agregar, simplemente usa esta consulta directa)
            val bdConsulta = admin.readableDatabase
            val cursor = bdConsulta.rawQuery("SELECT numero FROM contactos WHERE numero='$newNumero'", null)

            if (cursor.moveToFirst()) {
                Toast.makeText(this, "Este teléfono ya existe en tus contactos", Toast.LENGTH_SHORT).show()
                cursor.close()
                bdConsulta.close()
                return@setOnClickListener
            }
            cursor.close()

            val registro = ContentValues()
            registro.put("nombre", newNombre)
            registro.put("numero", newNumero)

            val cant = bd.update("contactos",registro,"numero='$ogNumero'",null)
            bd.close()
            if(cant==1){
                ogNombre = newNombre
                ogNumero = newNumero
                Toast.makeText(this,"Se modificaron los datos del contacto",Toast.LENGTH_SHORT).show()
            }else
                Toast.makeText(this,"Error al modificar cambios",Toast.LENGTH_SHORT).show()
        }
        btnEliminar.setOnClickListener {
            val admin = AdminSQLiteOpenHelper(this, "administracion", null, 1)
            val bd = admin.writableDatabase

            val cant = bd.delete("contactos", "numero='$ogNumero'", null)
            bd.close()

            if (cant==1){
                Toast.makeText(this, "Contacto eliminado", Toast.LENGTH_SHORT).show()
                finish()
            } else {
                Toast.makeText(this, "Error al eliminar contacto", Toast.LENGTH_SHORT).show()
            }
        }
    }
}