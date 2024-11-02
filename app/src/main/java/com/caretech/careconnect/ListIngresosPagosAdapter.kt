package com.caretech.careconnect

import android.content.Context
import android.graphics.Color
import android.graphics.PorterDuff
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class ListIngresosPagosAdapter(
    private val context: Context,
    private var list: MutableList<ListElementIngresosPagos>
) : RecyclerView.Adapter<ListIngresosPagosAdapter.ViewHolder>() {

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.prototype_ingresos_pagos, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(list[position])
    }

    fun setItems(items: List<ListElementIngresosPagos>) {
        list.clear()
        list.addAll(items)
        notifyDataSetChanged()
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val iconImage: ImageView = itemView.findViewById(R.id.iconImageView)
        private val pacienteText: TextView = itemView.findViewById(R.id.nameTextView)
        private val descriptionText: TextView = itemView.findViewById(R.id.descriptionTextView)
        private val montoText: TextView = itemView.findViewById(R.id.montoTextView)

        fun bind(item: ListElementIngresosPagos) {
            iconImage.setColorFilter(Color.parseColor(item.color), PorterDuff.Mode.SRC_IN)
            pacienteText.text = item.paciente
            descriptionText.text = item.descripcion
            montoText.text = item.monto
        }
    }
}
