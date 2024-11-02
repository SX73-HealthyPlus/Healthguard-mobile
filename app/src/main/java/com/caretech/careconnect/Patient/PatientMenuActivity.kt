package com.caretech.careconnect.Patient

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.ImageButton
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.caretech.careconnect.Doctor.DoctorsCitaActivity
import com.caretech.careconnect.MedicalHistory.HistorialMedicoActivity
import com.caretech.careconnect.R
import com.caretech.careconnect.models.Doctor
import com.caretech.careconnect.models.Patient

class PatientMenuActivity : AppCompatActivity() {
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {

        val doctor = intent.getSerializableExtra("doctor") as? Doctor
        val patient = intent.getSerializableExtra("patient") as? Patient
        val number = 13

        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_patient_menu)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        //BtnPerfil
        val btnPerfil = findViewById<ImageButton>(R.id.ibPerfil)

        btnPerfil.setOnClickListener {
            val intent = Intent(this, PatientViewProfileActivity::class.java)
            if(doctor != null){
                intent.putExtra("doctor", doctor as Doctor)
            }
            if(patient != null){
                intent.putExtra("patient", patient as Patient)
            }
            startActivity(intent)
        }

        val btHMedico = findViewById<Button>(R.id.btHMedico)

        btHMedico.setOnClickListener{
            val intent = Intent(this, HistorialMedicoActivity::class.java)
            if(doctor != null){
                intent.putExtra("doctor", doctor as Doctor)
            }
            if(patient != null){
                intent.putExtra("patient", patient as Patient)
            }

            startActivity(intent)
        }

        val btSacarCita = findViewById<Button>(R.id.btSacarCita)

        btSacarCita.setOnClickListener{
            val intent = Intent(this, DoctorsCitaActivity::class.java)
            if (doctor != null) {
                intent.putExtra("doctor", doctor as Doctor)
            }
            if (patient != null) {
                intent.putExtra("patient", patient as Patient)
            }
            startActivity(intent)
        }
    }
}