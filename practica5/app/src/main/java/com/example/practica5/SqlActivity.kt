package com.example.practica5

import android.content.ContentValues
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class SqlActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_sql)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        val txtCodigo = findViewById<EditText>(R.id.txtCodigo)
        val txtDescripcion = findViewById<EditText>(R.id.txtDescripcion)
        val txtPrecio = findViewById<EditText>(R.id.txtPrecio)
        val btnAlta = findViewById<Button>(R.id.btnAlta)
        val btnConsulta = findViewById<Button>(R.id.btnConsulta)
        val btnEliminar = findViewById<Button>(R.id.btnEliminar)
        val btnModificar = findViewById<Button>(R.id.btnModificar)

        btnAlta.setOnClickListener{
            val admin = AdminSQLiteOpenHelper(this, "administracion", null, 1)
            val bd = admin.writableDatabase

            val registro = ContentValues()
            registro.put("codigo",txtCodigo.text.toString())
            registro.put("descripcion",txtDescripcion.text.toString())
            registro.put("precio",txtPrecio.text.toString())

            bd.insert("articulos",null,registro)

            txtCodigo.setText("")
            txtDescripcion.setText("")
            txtPrecio.setText("")

            Toast.makeText(this,"Se guardaron los datos del articulo",Toast.LENGTH_SHORT).show()
            bd.close()
        }
        btnConsulta.setOnClickListener{
            val admin = AdminSQLiteOpenHelper(this, "administracion", null, 1)
            val bd = admin.writableDatabase

            val fila = bd.rawQuery("select descripcion, precio from articulos where codigo=${txtCodigo.text.toString()}",null)
            if (fila.moveToFirst()) {
                txtDescripcion.setText(fila.getString(0))
                txtPrecio.setText(fila.getString(0))
            }else
                Toast.makeText(this,"No existe un articulo con este codigo",Toast.LENGTH_SHORT).show()
            bd.close()
        }
        btnEliminar.setOnClickListener {
            val admin = AdminSQLiteOpenHelper(this,"administracion",null,1)
            val db = admin.writableDatabase

            val cant = db.delete("articulos","codigo=${txtCodigo.text.toString()}",null)
            txtCodigo.setText("")
            txtDescripcion.setText("")
            txtPrecio.setText("")
            if(cant==1){
                Toast.makeText(this,"Se borró el articulo con dicho codigo",Toast.LENGTH_SHORT).show()
            }else
                Toast.makeText(this,"No existe un articulo con dicho codigo",Toast.LENGTH_SHORT).show()
            db.close()
        }
        btnModificar.setOnClickListener {
            val admin = AdminSQLiteOpenHelper(this, "administracion",null,1)
            val db = admin.writableDatabase

            val registros = ContentValues()
            registros.put("descripcion",txtDescripcion.text.toString())
            registros.put("precio",txtPrecio.text.toString())

            val cant = db.update("articulos",registros,"codigo=${txtCodigo.text.toString()}",null)
            db.close()
            if(cant==1){
                Toast.makeText(this,"Se modificaron los datos del articulo",Toast.LENGTH_SHORT).show()
            }else
                Toast.makeText(this,"No existe u  articulo con dicho codigo",Toast.LENGTH_SHORT).show()
        }
    }
}
