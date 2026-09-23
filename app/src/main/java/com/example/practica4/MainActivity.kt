package com.example.practica4

import android.content.Intent
import android.os.Bundle
import android.widget.Button
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
        val btnToast = findViewById<Button>(R.id.btnToast)
        val btnList = findViewById<Button>(R.id.btnList)
        val btnParametros = findViewById<Button>(R.id.btnParametros)


        btnToast.setOnClickListener{
            val intento = Intent(this, toastActivity::class.java)
            startActivity(intento)
        }
        btnList.setOnClickListener{
            val intento = Intent(this, listActivity::class.java)
            startActivity(intento)
        }
        btnParametros.setOnClickListener{
            val intento = Intent(this, paramestrosActivity::class.java)
            startActivity(intento)
        }
    }
}