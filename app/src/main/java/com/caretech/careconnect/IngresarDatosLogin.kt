package com.caretech.careconnect

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.ImageButton
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.caretech.careconnect.Patient.PatientMenuActivity
import com.caretech.careconnect.network.RetrofitInstance
import com.caretech.careconnect.models.Doctor
import com.caretech.careconnect.models.DoctorLogin
import com.caretech.careconnect.models.Patient
import com.caretech.careconnect.models.PatientLogin
import com.google.android.material.textfield.TextInputEditText
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.io.Serializable

class IngresarDatosLogin : AppCompatActivity() {

    private lateinit var etEmail: TextInputEditText
    private lateinit var etPassword: TextInputEditText
    override fun onCreate(savedInstanceState: Bundle?) {
        val opcion = intent.getStringExtra("opcion")

        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_ingresar_datos_login)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        etEmail = findViewById(R.id.etTitular)
        etPassword = findViewById(R.id.etPassword)

        //Boton back
        val btnBack = findViewById<ImageButton>(R.id.ibBack)

        btnBack.setOnClickListener {
            val intent = Intent(this, RolCuentaActivity::class.java)
            startActivity(intent)
        }

        //Opcion tvNoAccount
        val tvNoAccount = findViewById<TextView>(R.id.tvNoAccount)

        tvNoAccount.setOnClickListener {
            val intent = Intent(this, IngresarDatosRegistro::class.java)
            startActivity(intent)
        }

        //Opcion Login
        val btnLogin = findViewById<Button>(R.id.btLog)

        btnLogin.setOnClickListener {


            val email = etEmail.text.toString()
            val password = etPassword.text.toString()

            if (email.isNotEmpty() && password.isNotEmpty()) {

                if(opcion == "paciente"){
                    val patient = PatientLogin(
                        id = 0,
                        name = "",
                        lastname = "",
                        age = 0,
                        email = email,
                        password = password,
                        height = 0.0,
                        weight = 0.0,
                        body_mass_index = 0.0
                    )

                    RetrofitInstance.ApiPatient.loginPatient(patient).enqueue(object : Callback<Patient> {
                        override fun onResponse(call: Call<Patient>, response: Response<Patient>) {
                            if (response.isSuccessful) {
                                val loggedInPatient = response.body()
                                val intent = Intent(this@IngresarDatosLogin, PatientMenuActivity::class.java)
                                intent.putExtra("patient", loggedInPatient as Serializable)
                                startActivity(intent)
                            } else {
                                Toast.makeText(this@IngresarDatosLogin, "Login fallido: ${response.message()}", Toast.LENGTH_LONG).show()
                            }
                        }

                        override fun onFailure(call: Call<Patient>, t: Throwable) {
                            Toast.makeText(this@IngresarDatosLogin, "Error en la red: ${t.message}", Toast.LENGTH_LONG).show()
                        }
                    })
                }
                else if(opcion == "profesional"){
                        val doctor = DoctorLogin(
                        id = 0,
                        name = "",
                        lastname = "",
                        specialty = "",
                        email = email,
                        password = password,
                        height = 0.0,
                        weight = 0.0,
                        bodyMassIndex = 0.0
                    )

                    RetrofitInstance.ApiDoctor.loginDoctor(doctor).enqueue(object : Callback<Doctor> {
                        override fun onResponse(call: Call<Doctor>, response: Response<Doctor>) {
                            if (response.isSuccessful) {
                                val loggedInDoctor = response.body()
                                val intent = Intent(this@IngresarDatosLogin, PatientMenuActivity::class.java)
                                intent.putExtra("doctor", loggedInDoctor as Serializable)
                                startActivity(intent)
                            } else {
                                Toast.makeText(this@IngresarDatosLogin, "Login fallido: ${response.message()}", Toast.LENGTH_LONG).show()
                            }
                        }

                        override fun onFailure(call: Call<Doctor>, t: Throwable) {
                            Toast.makeText(this@IngresarDatosLogin, "Error en la red: ${t.message}", Toast.LENGTH_LONG).show()
                        }
                    })
                }

            } else {
                // Mostrar un mensaje de error si los campos están vacíos
                Toast.makeText(this, "Por favor, ingrese el email y la contraseña", Toast.LENGTH_LONG).show()
            }
        }

    }
}
