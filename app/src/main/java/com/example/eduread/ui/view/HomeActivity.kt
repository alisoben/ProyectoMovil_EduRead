package com.example.eduread.ui.view

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.eduread.MainActivity
import com.example.eduread.R

class HomeActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_home)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        val btnCuentos: Button = findViewById(R.id.buttonCuentos)
        btnCuentos.setOnClickListener(){
            val intent2: Intent = Intent(this, CuentosActivity:: class.java)
            startActivity(intent2)
        }
        val btnCerrarSeccion: Button = findViewById(R.id.buttonCerrarSesion)
        btnCerrarSeccion.setOnClickListener(){
            val intent3 : Intent = Intent(this,MainActivity:: class.java)
            startActivity((intent3))
        }

    }
}