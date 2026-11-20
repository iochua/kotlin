package com.example.practica7

import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.practica7.models.User
import io.github.jan.supabase.SupabaseClient
import io.github.jan.supabase.createSupabaseClient
import io.github.jan.supabase.postgrest.Postgrest
import io.github.jan.supabase.postgrest.postgrest
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class activitySupabase : AppCompatActivity() {
    private lateinit var txtResultado: TextView
    private lateinit var btnCargarUsuarios: Button
    private val supabase: SupabaseClient by lazy {
        createSupabaseClient(
            supabaseUrl = "https://exeerlxsgreiigbojnml.supabase.co/",
            supabaseKey = "sb_publishable_sOl1dFlPsj0JlcUknyuf4Q_qekmIQ1B"
        ){
            install(Postgrest)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_supabase)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        txtResultado = findViewById<TextView>(R.id.txtResultado)
        btnCargarUsuarios = findViewById<Button>(R.id.btnCargarUsuarios)

        btnCargarUsuarios.setOnClickListener {
            loadUsers()
        }
    }
    private fun loadUsers() {
        CoroutineScope(Dispatchers.IO).launch {
            try {
                 //select * from users
                val users = supabase.postgrest["users"]
                    .select()
                    .decodeList<User>()
                val resultText = users.joinToString("\n") {
                    "${it.user} (Email: ${it.email})"
                }

            } catch (e: Exception){
                runOnUiThread {
                    txtResultado.text = "Error: ${e.localizedMessage}"
                }
            }

        }
    }
}