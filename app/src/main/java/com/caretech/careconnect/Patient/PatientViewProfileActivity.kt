package com.caretech.careconnect.Patient

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.bumptech.glide.Glide
import com.caretech.careconnect.MedicalHistory.HistorialMedicoActivity
import com.caretech.careconnect.R
import com.caretech.careconnect.models.Patient
import com.caretech.careconnect.network.PatientService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.text.SimpleDateFormat
import java.util.Locale

class PatientViewProfileActivity : AppCompatActivity() {
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_patient_view_profile)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        getPersonalInformation()

        //BtnLeerInfoPersonal
        val btnLeerInfoPersonal = findViewById<Button>(R.id.btLeerInfoPersonal)

        btnLeerInfoPersonal.setOnClickListener {
            val intent = Intent(this, PatientEditPersonalInformationActivity::class.java)
            startActivity(intent)
        }

        //BtnEdit
        val btnEdit = findViewById<ImageButton>(R.id.ibEdit)
        btnEdit.setOnClickListener {
            val intent = Intent(this, PatientEditPersonalInformationActivity::class.java)
            startActivity(intent)
        }

        //BtnHistorialMedico
        val btnHistorialMedico = findViewById<Button>(R.id.btLeerHMedico)
        btnHistorialMedico.setOnClickListener {
            val intent = Intent(this, HistorialMedicoActivity::class.java)
            startActivity(intent)
        }


        //BotonBack
        val btnBack = findViewById<ImageButton>(R.id.ibBackWhite)

        btnBack.setOnClickListener {
            val intent = Intent(this, PatientMenuActivity::class.java)
            startActivity(intent)
        }

        //BtnActualizar
        val btnActualizar = findViewById<ImageButton>(R.id.ibPerfil)
        btnActualizar.setOnClickListener {
            getPersonalInformation()
        }

    }

    private fun getPersonalInformation() {
        val tvNombre = findViewById<TextView>(R.id.tvNombre)
        val tvApellido = findViewById<TextView>(R.id.tvApellido)
        val tvFechaDeNacimiento = findViewById<TextView>(R.id.tvFecha)
        val tvTelefono = findViewById<TextView>(R.id.tvTelefono)
        val ivFoto = findViewById<ImageView>(R.id.ivFoto)

        //Redireccionar a la vista de perfil
        val intent = Intent(this, PatientViewProfileActivity::class.java)
        startActivity(intent)
        //Instancia de Retrofit
        val retrofit = Retrofit.Builder()
            .baseUrl("https://")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        //Instancia de nuestro service
        val patientService : PatientService = retrofit.create(PatientService::class.java)

        val request = patientService.getPersonalInformationToViewProfile("json")

        request.enqueue(object : Callback<Patient> {
            override fun onResponse(call: Call<Patient>, response: Response<Patient>) {
                if (response.isSuccessful) {
                    val patient = response.body()!!
                    tvNombre.text = patient.name
                    tvApellido.text = patient.lastname
                    //Formateo de fecha
                    val dateFormat = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault())
                    val formattedDate = dateFormat.format(patient.birthdate)
                    tvFechaDeNacimiento.text = formattedDate
                    tvTelefono.text = patient.phone
                    //Mostrar Imagen
                    Glide.with(this@PatientViewProfileActivity)
                        .load(patient.photo)
                        .into(ivFoto)
                }
            }

            override fun onFailure(call: Call<Patient>, t: Throwable) {
                Log.e("Error", t.message.toString())
            }
        })
    }
}