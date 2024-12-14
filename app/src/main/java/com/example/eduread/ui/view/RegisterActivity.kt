package com.example.eduread.ui.view

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.eduread.R
import com.example.eduread.ui.manager.RegisterManager

class RegisterActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_register)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        // Referencias a los campos
       val usernameEditText = findViewById<EditText>(R.id.nameEditText)
        val ageEditText = findViewById<EditText>(R.id.ageEditText)
        val passwordEditText = findViewById<EditText>(R.id.passwordEditText)
        val registerButton = findViewById<Button>(R.id.registerButton)
        val loginSubText = findViewById<TextView>(R.id.loginSubText)

        // Listener del botón Registrar
        registerButton.setOnClickListener {
            val nombre = usernameEditText.text.toString().trim()
            val edad = ageEditText.text.toString().toIntOrNull()
            val clave = passwordEditText.text.toString().trim()

            if (nombre.isNotEmpty() && edad != null && clave.isNotEmpty()) {
                RegisterManager.register(this, nombre, clave, edad) { success, message ->
                    if (success) {
                        Toast.makeText(this, "Registro exitoso: $message", Toast.LENGTH_SHORT).show()
                        // Opcional: Redirigir a LoginActivity
                        finish()
                    } else {
                        Toast.makeText(this, "Error: $message", Toast.LENGTH_SHORT).show()
                    }
                }
            } else {
                Toast.makeText(this, "Por favor, llena todos los campos correctamente", Toast.LENGTH_SHORT).show()
            }
        }
        //Listener del iniciar sesion
        loginSubText.setOnClickListener {
            // Redirige al LoginActivity
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
        }

        }
}
