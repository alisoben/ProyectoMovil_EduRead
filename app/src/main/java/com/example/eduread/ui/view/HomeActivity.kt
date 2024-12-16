package com.example.eduread.ui.view

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.eduread.R

class HomeActivity : AppCompatActivity() {
    private var userId: Int = -1//Agregué
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_home)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        userId = intent.getIntExtra("usuario_id", -1)//Agregué

        val btnCuentos: Button = findViewById(R.id.buttonCuentos)
        btnCuentos.setOnClickListener(){
            val intent: Intent = Intent(this, CuentosActivity:: class.java)//agregué cambiar de intent 2 a intent
            intent.putExtra("usuario_id", userId)//Agregué
            startActivity(intent)//intent2 ->intent
        }

    }
}