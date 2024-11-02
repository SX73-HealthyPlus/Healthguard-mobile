package com.caretech.careconnect.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.Adapter
import com.caretech.careconnect.R
import com.caretech.careconnect.models.Alergia

class AlergiaHistorialAdapter(var alergias: ArrayList<Alergia>): Adapter<AlergiasPrototype>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AlergiasPrototype {
        //"Infla" los datos en un prototype
        val view = LayoutInflater
            .from(parent.context)
            .inflate(R.layout.prototype_alergiahistorial, parent, false)

        return AlergiasPrototype(view)
    }


    override fun onBindViewHolder(holder: AlergiasPrototype, position: Int) {
        //vincula la data con el prototype
        holder.bind(alergias.get(position))
    }

    override fun getItemCount(): Int {
        return alergias.size
    }

}

class AlergiasPrototype (itemView: View) : RecyclerView.ViewHolder(itemView){

    val tvAlergia = itemView.findViewById<TextView>(R.id.tvAlergia)

    fun bind(alergia: Alergia){
        tvAlergia.text =alergia.alergia
    }

}

