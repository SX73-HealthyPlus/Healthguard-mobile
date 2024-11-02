package com.caretech.careconnect

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.CalendarView
import android.widget.TimePicker
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.caretech.careconnect.Payment.PagoCitaActivity

class SeleccionarFechaActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_seleccionar_fecha)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val btnSiguiente = findViewById<Button>(R.id.btSiguiente)

        btnSiguiente.setOnClickListener {
            val intent = Intent(this, PagoCitaActivity::class.java)
            startActivity(intent)
        }

        val cvFecha = findViewById<CalendarView>(R.id.cvFecha)
        val tpHorario = findViewById<TimePicker>(R.id.tpHorario)

        //capturar la fecha seleccionada
        cvFecha.setOnDateChangeListener { view, year, month, dayOfMonth ->
            val fecha = "$dayOfMonth/$month/$year"
        }

        //capturar la hora seleccionada
        tpHorario.setOnTimeChangedListener { view, hourOfDay, minute ->
            val hora = "$hourOfDay:$minute"
        }



    }
}