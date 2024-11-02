package com.caretech.careconnect.Patient

import android.os.Bundle
import android.util.Log
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.caretech.careconnect.R
import com.caretech.careconnect.adapter.PatientAdapter
import com.caretech.careconnect.models.Patient
import com.caretech.careconnect.network.PatientService
import com.google.android.material.textfield.TextInputEditText
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class PatientsCitaActivity : AppCompatActivity() {
    lateinit var patients: List<Patient>
    lateinit var patientAdapter: PatientAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_patients_cita)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val btnSearchPatients = findViewById<Button>(R.id.btSearchClientes)

        btnSearchPatients.setOnClickListener{
            searchPatient()
        }

    }

    private fun searchPatient() {
        val etNombrePaciente = findViewById<TextInputEditText>(R.id.etNombrePaciente)
        val nombrePaciente = etNombrePaciente.text.toString()

        //Instancia de retrofit
        val retrofit = Retrofit.Builder()
            .baseUrl("https://")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        //Instancia de nuestro service
        val patientService: PatientService = retrofit.create(PatientService::class.java)

        val request = patientService.searchPatient(nombrePaciente)

        request.enqueue(object : Callback<List<Patient>> {
            override fun onResponse(p0: Call<List<Patient>>, p1: Response<List<Patient>>) {
                val rvPatients = findViewById<RecyclerView>(R.id.rvPatients)
                patients = p1.body()!!
                patientAdapter = PatientAdapter(patients)
                rvPatients.adapter = patientAdapter
                rvPatients.layoutManager = LinearLayoutManager(this@PatientsCitaActivity)
            }

            override fun onFailure(p0: Call<List<Patient>>, p1: Throwable) {
                Log.e("Error", p1.message.toString())
            }

        })
    }
}