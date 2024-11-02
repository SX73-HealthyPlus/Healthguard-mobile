package com.caretech.careconnect

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class IngresosPagosActivity : AppCompatActivity() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: ListIngresosPagosAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ingresos_pagos)

        recyclerView = findViewById(R.id.listRecyclerView)
        recyclerView.layoutManager = LinearLayoutManager(this)
        adapter = ListIngresosPagosAdapter(this, mutableListOf())
        recyclerView.adapter = adapter

        fetchDataFromApi()
    }

    private fun fetchDataFromApi() {
        val retrofit = Retrofit.Builder()
            .baseUrl("https://your-base-url.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        val service = retrofit.create(PagoService::class.java)
        service.getIngresosPagos().enqueue(object : Callback<List<ListElementIngresosPagos>> {
            override fun onResponse(call: Call<List<ListElementIngresosPagos>>, response: Response<List<ListElementIngresosPagos>>) {
                if (response.isSuccessful) {
                    response.body()?.let { data ->
                        adapter.setItems(data)
                    }
                } else {
                    showError("Error en la respuesta del servidor")
                }
            }

            override fun onFailure(call: Call<List<ListElementIngresosPagos>>, t: Throwable) {
                showError("Error en la conexión: ${t.message}")
            }
        })
    }

    private fun showError(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_LONG).show()
    }
}
