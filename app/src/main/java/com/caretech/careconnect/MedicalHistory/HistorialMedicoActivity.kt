package com.caretech.careconnect.MedicalHistory

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.caretech.careconnect.R
import com.caretech.careconnect.models.Patient

class HistorialMedicoActivity : AppCompatActivity() {
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        val patient = intent.getSerializableExtra("patient") as? Patient


        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_historial_medico)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val nombrePaciente = findViewById<TextView>(R.id.tvNombrePacienteResumen)
        nombrePaciente.text = patient?.name + " " + patient?.lastname

        val edadPaciente = findViewById<TextView>(R.id.tvEdad)
        edadPaciente.text = patient?.age.toString()

        val btAdjuntar = findViewById<Button>(R.id.btAdjuntar)

        btAdjuntar.setOnClickListener{
            val intent = Intent(this, HistorialMedicoResumenActivity::class.java)
            startActivity(intent)
        }


    }

}