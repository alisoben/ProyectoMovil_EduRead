package com.example.eduread.ui.view

import androidx.appcompat.app.AlertDialog

import android.content.Intent
import android.os.Bundle
import android.view.WindowManager
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
        val confirmPasswordEditText = findViewById<EditText>(R.id.confirmPasswordEditText)
        val registerButton = findViewById<Button>(R.id.registerButton)
        val loginSubText = findViewById<TextView>(R.id.loginSubText)

        // Listener del botón Registrar
        registerButton.setOnClickListener {
            val nombre = usernameEditText.text.toString().trim()
            val edad = ageEditText.text.toString().toIntOrNull()
            val clave = passwordEditText.text.toString().trim()
            val claveConfirmacion = confirmPasswordEditText.text.toString().trim()

            if (nombre.isNotEmpty() && edad != null && clave.isNotEmpty() && claveConfirmacion.isNotEmpty()) {
                if (clave != claveConfirmacion) {
                    showErrorDialog("Las contraseñas no coinciden. Por favor, verifica e inténtalo de nuevo.")
                    return@setOnClickListener
                }

                RegisterManager.register(this, nombre, clave, edad) { success, message ->
                    if (success) {
                        showSuccessDialog("¡Registro exitoso!", "Bienvenido a EduRead.")
                    } else {
                        showErrorDialog("Error: $message")
                    }
                }
            } else {
                showErrorDialog("Por favor, completa todos los campos correctamente.")
            }
        }
        //Listener del iniciar sesion
        loginSubText.setOnClickListener {
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
        }
    }

    private fun showErrorDialog(message: String) {
        AlertDialog.Builder(this)
            .setTitle("Error")
            .setMessage(message)
            .setPositiveButton("Aceptar", null)
            .show()
    }

    private fun showSuccessDialog(title: String, message: String) {
        val builder = AlertDialog.Builder(this)
        builder.setTitle(title)
        builder.setMessage(message)
        builder.setPositiveButton("Aceptar") { dialog, _ -> dialog.dismiss()
            finish() }
        val dialog = builder.create()
        dialog.show()

        // Personalizar tamaño del cuadro de diálogo
        val width = resources.displayMetrics.widthPixels * 0.9
        dialog.window?.setLayout(width.toInt(), WindowManager.LayoutParams.WRAP_CONTENT)
    }
}
