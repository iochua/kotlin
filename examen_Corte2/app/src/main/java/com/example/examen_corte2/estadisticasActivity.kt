package com.example.examen_corte2

import android.os.Bundle
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class estadisticasActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_estadisticas)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        val txtP1R1 = findViewById<TextView>(R.id.txtP1R1)
        val txtP1R2 = findViewById<TextView>(R.id.txtP1R2)
        val txtP1R3 = findViewById<TextView>(R.id.txtP1R3)
        val txtP1R4 = findViewById<TextView>(R.id.txtP1R4)

        val txtP2R1 = findViewById<TextView>(R.id.txtP2R1)
        val txtP2R2 = findViewById<TextView>(R.id.txtP2R2)
        val txtP2R3 = findViewById<TextView>(R.id.txtP2R3)
        val txtP2R4 = findViewById<TextView>(R.id.txtP2R4)

        val txtP3R1 = findViewById<TextView>(R.id.txtP3R1)
        val txtP3R2 = findViewById<TextView>(R.id.txtP3R2)
        val txtP3R3 = findViewById<TextView>(R.id.txtP3R3)
        val txtP3R4 = findViewById<TextView>(R.id.txtP3R4)

        val txtP4R1 = findViewById<TextView>(R.id.txtP4R1)
        val txtP4R2 = findViewById<TextView>(R.id.txtP4R2)
        val txtP4R3 = findViewById<TextView>(R.id.txtP4R3)
        val txtP4R4 = findViewById<TextView>(R.id.txtP4R4)

        val txtP5R1 = findViewById<TextView>(R.id.txtP5R1)
        val txtP5R2 = findViewById<TextView>(R.id.txtP5R2)
        val txtP5R3 = findViewById<TextView>(R.id.txtP5R3)

        val admin = AdminSQLiteOpenHelper(this, "administracion", null, 1)
        val bd = admin.readableDatabase

        val cursor = bd.rawQuery("SELECT primera, segunda, tercera, cuarta, quinta FROM respuestas", null)

        var p1r1 = 0; var p1r2 = 0; var p1r3 = 0; var p1r4 = 0
        var p2r1 = 0; var p2r2 = 0; var p2r3 = 0; var p2r4 = 0
        var p3r1 = 0; var p3r2 = 0; var p3r3 = 0; var p3r4 = 0
        var p4r1 = 0; var p4r2 = 0; var p4r3 = 0; var p4r4 = 0
        var p5r1 = 0; var p5r2 = 0; var p5r3 = 0

        while (cursor.moveToNext()) {
            val resP1 = cursor.getInt(0)
            val resP2 = cursor.getInt(1)
            val resP3 = cursor.getInt(2)
            val resP4 = cursor.getInt(3)
            val resP5 = cursor.getInt(4)

            when (resP1) {
                1 -> p1r1++
                2 -> p1r2++
                3 -> p1r3++
                4 -> p1r4++
            }

            when (resP2) {
                1 -> p2r1++
                2 -> p2r2++
                3 -> p2r3++
                4 -> p2r4++
            }

            when (resP3) {
                1 -> p3r1++
                2 -> p3r2++
                3 -> p3r3++
                4 -> p3r4++
            }

            when (resP4) {
                1 -> p4r1++
                2 -> p4r2++
                3 -> p4r3++
                4 -> p4r4++
            }

            when (resP5) {
                1 -> p5r1++
                2 -> p5r2++
                3 -> p5r3++
            }
        }

        cursor.close()
        bd.close()

        txtP1R1.text = "1 vez al mes: $p1r1"
        txtP1R2.text = "1 vez cada semana: $p1r2"
        txtP1R3.text = "Más de una vez a la semana: $p1r3"
        txtP1R4.text = "Casi nunca: $p1r4"

        txtP2R1.text = "Malo: $p2r1"
        txtP2R2.text = "Regular: $p2r2"
        txtP2R3.text = "Bueno: $p2r3"
        txtP2R4.text = "Excelente: $p2r4"

        txtP3R1.text = "Malo: $p3r1"
        txtP3R2.text = "Regular: $p3r2"
        txtP3R3.text = "Bueno: $p3r3"
        txtP3R4.text = "Excelente: $p3r4"

        txtP4R1.text = "Malo: $p4r1"
        txtP4R2.text = "Regular: $p4r2"
        txtP4R3.text = "Bueno: $p4r3"
        txtP4R4.text = "Excelente: $p4r4"

        txtP5R1.text = "3D: $p5r1"
        txtP5R2.text = "Sala normal: $p5r2"
        txtP5R3.text = "Macro XE: $p5r3"
    }
}