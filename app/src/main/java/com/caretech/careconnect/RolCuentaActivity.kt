package com.caretech.careconnect

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.ImageButton
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class RolCuentaActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_rol_cuenta)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        //Boton back
        val btnBack = findViewById<ImageButton>(R.id.btBack)

        btnBack.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }

        //Boton profesional
        val btnProfesional = findViewById<Button>(R.id.btProfesional)

        btnProfesional.setOnClickListener {
            val intent = Intent(this, IngresarDatosLogin::class.java)
            intent.putExtra("opcion", "profesional")
            startActivity(intent)
        }

        //Boton paciente
        val btnPaciente = findViewById<Button>(R.id.btPaciente)

        btnPaciente.setOnClickListener {
            val intent = Intent(this, IngresarDatosLogin::class.java)
            intent.putExtra("opcion", "paciente")
            startActivity(intent)
        }
    }
}