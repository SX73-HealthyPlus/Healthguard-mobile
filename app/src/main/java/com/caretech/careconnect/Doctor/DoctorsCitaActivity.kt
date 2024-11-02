package com.caretech.careconnect.Doctor

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
import com.caretech.careconnect.adapter.DoctorAdapter
import com.caretech.careconnect.models.Doctor
import com.caretech.careconnect.network.CitaService
import com.google.android.material.textfield.TextInputEditText
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class DoctorsCitaActivity : AppCompatActivity() {
    lateinit var doctors: List<Doctor>
    lateinit var doctorAdapter: DoctorAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_doctors_cita)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        val btnSearch = findViewById<Button>(R.id.btSearchDoctores)

        btnSearch.setOnClickListener{
            searchDoctor()
        }
    }

    private fun searchDoctor() {
        val etNombreDoctor = findViewById<TextInputEditText>(R.id.etSearchNombreDoctor)
        val nombreDoctor = etNombreDoctor.text.toString()

        //Instancia de retrofit
        val retrofit = Retrofit.Builder()
            .baseUrl("https://")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        //Instancia de nuestro service
        val citaService: CitaService = retrofit.create(CitaService::class.java)

        val request = citaService.searchDoctor(nombreDoctor)

        request.enqueue(object : Callback<List<Doctor>>{
            override fun onResponse(p0: Call<List<Doctor>>, p1: Response<List<Doctor>>) {
                val rvDoctor = findViewById<RecyclerView>(R.id.rvDoctores)
                doctors = p1.body()!!
                doctorAdapter = DoctorAdapter(doctors)
                rvDoctor.adapter = doctorAdapter
                rvDoctor.layoutManager = LinearLayoutManager(this@DoctorsCitaActivity)
            }

            override fun onFailure(p0: Call<List<Doctor>>, p1: Throwable) {
                Log.e("Error", p1.message.toString())
            }

        })
    }
}