package com.caretech.careconnect.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView.Adapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.bumptech.glide.Glide
import com.caretech.careconnect.R
import com.caretech.careconnect.models.Patient

class PatientAdapter(val patients: List<Patient>) : Adapter<PatientPrototype>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PatientPrototype {
        //Inflar la vista
        val view = LayoutInflater
            .from(parent.context)
            .inflate(R.layout.prototype_patients_cita, parent, false)

        return PatientPrototype(view)
    }

    override fun getItemCount(): Int {
        return patients.size
    }

    override fun onBindViewHolder(holder: PatientPrototype, position: Int) {
        holder.bind(patients[position])
    }

}

class PatientPrototype(itemView: View) : ViewHolder(itemView) {
    val tvNombrePaciente = itemView.findViewById<TextView>(R.id.tvNombrePaciente)
    val tvEdadPaciente = itemView.findViewById<TextView>(R.id.tvEdadPaciente)
    val ivPacienteFoto = itemView.findViewById<ImageView>(R.id.ivPacienteFoto)

    @SuppressLint("SetTextI18n")
    fun bind(patient : Patient){
        tvNombrePaciente.text = patient.name + " " + patient.lastname
        tvEdadPaciente.text = patient.age.toString()
        //Mostrar la imagen del paciente
        Glide.with(itemView)
            .load(patient.photo)
            .into(ivPacienteFoto)
    }

}
