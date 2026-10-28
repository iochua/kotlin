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
        var ogNumero = intent.getStringExtra("numeroContacto")

        val admin = AdminSQLiteOpenHelper(this, "administracion", null, 1)
        val bd = admin.readableDatabase
        val fila = bd.rawQuery("SELECT nombre, email, direccion, notas FROM contactos WHERE numero='$ogNumero'", null)

        if (fila.moveToFirst()) {
            txtNombre.setText(fila.getString(0) ?: "")
            txtEmail.setText(fila.getString(1) ?: "")
            txtDireccion.setText(fila.getString(2) ?: "")
            txtNotas.setText(fila.getString(3) ?: "")
        }
        fila.close()
        bd.close()

        txtNumero.setText(ogNumero)

        btnGuardar.setOnClickListener {
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
            val admin = AdminSQLiteOpenHelper(this, "administracion", null, 1)
            val bdConsulta = admin.readableDatabase
            val fila = bdConsulta.rawQuery("SELECT numero FROM contactos WHERE numero='$newNumero'", null)

            if (fila.moveToFirst() && newNumero != ogNumero) {
                Toast.makeText(this, "Ya existe un contacto con este número", Toast.LENGTH_SHORT).show()
                fila.close()
                bdConsulta.close()
                return@setOnClickListener
            }
            fila.close()
            bdConsulta.close()

            val bdEscritura = admin.writableDatabase
            val registro = ContentValues()
            registro.put("nombre", newNombre)
            registro.put("numero", newNumero)

            if (email.isNotEmpty())
                registro.put("email",email)
            else registro.putNull("email")
            if (direccion.isNotEmpty())
                registro.put("direccion",direccion)
            else registro.putNull("direccion")
            if (notas.isNotEmpty())
                registro.put("notas",notas)
            else registro.putNull("notas")

            val cant = bdEscritura.update("contactos",registro,"numero='$ogNumero'",null)
            bdEscritura.close()

            if(cant==1){
                ogNumero = newNumero
                Toast.makeText(this,"Se modificaron los datos del contacto",Toast.LENGTH_SHORT).show()
            }else
                Toast.makeText(this,"Error al modificar cambios",Toast.LENGTH_SHORT).show()
        }
        var confirmar = false
        btnEliminar.setOnClickListener {
            if (!confirmar) {
                Toast.makeText(this, "Da clic para confirmar la acción", Toast.LENGTH_SHORT).show()
                confirmar = true
            } else {
                val admin = AdminSQLiteOpenHelper(this, "administracion", null, 1)
                val bd = admin.writableDatabase

                val cant = bd.delete("contactos", "numero='$ogNumero'", null)
                bd.close()

                if (cant == 1) {
                    Toast.makeText(this, "Contacto eliminado", Toast.LENGTH_SHORT).show()
                    val intento = Intent(this, contactosActivity::class.java)
                    intento.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
                    startActivity(intento)
                } else {
                    Toast.makeText(this, "Error al eliminar contacto", Toast.LENGTH_SHORT).show()
                }
            }
        }
        btnSalir.setOnClickListener {
            val intento = Intent(this, contactosActivity::class.java)
            intento.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
            startActivity(intento)
        }
    }
}