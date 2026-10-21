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
        val txtEmail = findViewById<EditText>(R.id.txtEmail2)
        val txtDireccion = findViewById<EditText>(R.id.txtDireccion2)
        val txtNotas = findViewById<EditText>(R.id.txtNotas2)
        val btnGuardar = findViewById<Button>(R.id.btnGuardar2)
        val btnEliminar = findViewById<Button>(R.id.btnEliminar)
        val btnSalir = findViewById<Button>(R.id.btnSalir2)
        var ogNombre = intent.getStringExtra("nombreContacto")
        var ogNumero = intent.getStringExtra("numeroContacto")

        val admin = AdminSQLiteOpenHelper(this, "administracion", null, 1)
        val bd = admin.readableDatabase
        val cursor = bd.rawQuery("SELECT email, direccion, notas FROM contactos WHERE numero='$ogNumero'", null)

        if (cursor.moveToFirst()) {
            txtEmail.setText(cursor.getString(0) ?: "")
            txtDireccion.setText(cursor.getString(1) ?: "")
            txtNotas.setText(cursor.getString(2) ?: "")
        }
        cursor.close()
        bd.close()

        txtNombre.setText(ogNombre)
        txtNumero.setText(ogNumero)

        btnGuardar.setOnClickListener {
            val admin = AdminSQLiteOpenHelper(this, "administracion", null, 1)
            val bd = admin.writableDatabase
            val newNombre = txtNombre.text.toString().trim()
            val newNumero = txtNumero.text.toString().trim()
            val email = txtEmail.text.toString().trim()
            val direccion = txtDireccion.text.toString().trim()
            val notas = txtNotas.text.toString().trim()

            if (newNombre.isEmpty() || newNumero.isEmpty()) {
                Toast.makeText(this, "El nombre y/o número no pueden estar vacíos", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            if (newNumero.length < 7) {
                Toast.makeText(this, "El teléfono debe tener al menos 7 dígitos", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            //cambiar el nombre de estas variables
            val bdConsulta = admin.readableDatabase
            val cursor = bdConsulta.rawQuery("SELECT numero FROM contactos WHERE numero='$newNumero'", null)

            if (cursor.moveToFirst()) {
                Toast.makeText(this, "Ya existe un contacto con este número", Toast.LENGTH_SHORT).show()
                cursor.close()
                bdConsulta.close()
                return@setOnClickListener
            }
            cursor.close()

            val registro = ContentValues()
            registro.put("nombre", newNombre)
            registro.put("numero", newNumero)
            if (email.isNotEmpty())
                registro.put("email",email)
            else registro.putNull("email", email)
            if (direccion.isNotEmpty())
                registro.put("direccion",direccion)
            else registro.putNull("direccion", direccion)
            if (notas.isNotEmpty())
                registro.put("notas",notas)
            else registro.putNull("notas", notas)

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