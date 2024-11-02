package com.caretech.careconnect.Payment

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.caretech.careconnect.R
import com.google.android.material.textfield.TextInputEditText

class PagoCitaActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_pago_cita)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val btnConfirmar = findViewById<Button>(R.id.btConfirmar)

        btnConfirmar.setOnClickListener {
            val intent = Intent(this, PagoRealizadoActivity::class.java)
            startActivity(intent)
        }

        val etTitular = findViewById<TextInputEditText>(R.id.etTitular)
        val etTarjeta = findViewById<TextInputEditText>(R.id.etTarjeta)
        val etVencimiento = findViewById<TextInputEditText>(R.id.etVencimiento)
        val etCVV = findViewById<TextInputEditText>(R.id.etCvv)


        //capturar los datos de los campos de texto
        val titular = etTitular.text.toString()
        val tarjeta = etTarjeta.text.toString()
        val vencimiento = etVencimiento.text.toString()
        val cvv = etCVV.text.toString()


    }
}