package com.caretech.careconnect

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.caretech.careconnect.models.Alergia

class MainActivity : AppCompatActivity() {

    val alergias =ArrayList<Alergia>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        //Boton Sign Up
        val btnSingUp = findViewById<Button>(R.id.btSignUp)

        btnSingUp.setOnClickListener {
            val intent = Intent(this, RolInteractivityRegister::class.java)
            startActivity(intent)
        }

        //Boton login
        val btnLogin = findViewById<Button>(R.id.btLogIn)

        btnLogin.setOnClickListener {
            val intent = Intent(this, RolCuentaActivity::class.java)
            startActivity(intent)
        }


    }






}