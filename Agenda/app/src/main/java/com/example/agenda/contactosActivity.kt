package com.example.agenda

import android.content.Intent
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.ListView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class contactosActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_contactos)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        val listContactos = findViewById<ListView>(R.id.listContactos)
        val btnSalirLista = findViewById<Button>(R.id.btnSalirLista)

        val listaNombres = arrayListOf<String>()
        val listaNumeros = arrayListOf<String>()

        val admin = AdminSQLiteOpenHelper(this, "administracion", null, 1)
        val bd = admin.readableDatabase

        val fila = bd.rawQuery("SELECT nombre, numero FROM contactos ORDER BY nombre ASC", null)
        if (fila.moveToFirst()){
            do {
                listaNombres.add(fila.getString(0))
                listaNumeros.add(fila.getString(1))
            } while (fila.moveToNext())
        }
        bd.close()

        val adapter1 = ArrayAdapter<String>(this, android.R.layout.simple_list_item_1,listaNombres)
        listContactos.adapter = adapter1

        listContactos.setOnItemClickListener { parent, view, position, id ->
            val intento = Intent(this, editarContactoActivity::class.java)
            intento.putExtra("nombreContacto", listaNombres[position])
            intento.putExtra("numeroContacto", listaNumeros[position])
            startActivity(intento)
        }

        btnSalirLista.setOnClickListener {
            finish()
        }
    }
}