package com.example.eduread.ui.view

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.eduread.R
import com.example.eduread.ui.manager.LoginManager

class LoginActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        val inputUsuario: EditText = findViewById(R.id.editTextText)
        val inputContrasena: EditText = findViewById(R.id.editTextTextPassword)
        val btnIngresar: Button = findViewById(R.id.buttonIngresarHome)

        btnIngresar.setOnClickListener {
            val nombre = inputUsuario.text.toString().trim()
            val clave = inputContrasena.text.toString().trim()
            if (nombre.isNotEmpty() && clave.isNotEmpty()) {
                LoginManager.login(this, nombre, clave) { success, message, userId ->
                    if (success) {
                        Toast.makeText(this, "Bienvenido, $nombre", Toast.LENGTH_SHORT).show()
                        val intent = Intent(this, HomeActivity::class.java)
                        intent.putExtra("usuario_id", userId)
                        startActivity(intent)
                    } else {
                        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
                    }
                }
            } else {
                Toast.makeText(this, "Por favor, ingresa las credenciales", Toast.LENGTH_SHORT).show()
            }
        }
        val btnRegistro: Button = findViewById(R.id.buttonRegistrar)
        btnRegistro.setOnClickListener(){
            val intent: Intent = Intent(this, RegisterActivity:: class.java)
            startActivity(intent)
        }
    }
}
