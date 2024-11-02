package com.caretech.careconnect

import retrofit2.Call
import retrofit2.http.GET

interface PagoService {
    @GET("ingresos_pagos") // Reemplazar con el endpoint correcto
    fun getIngresosPagos(): Call<List<ListElementIngresosPagos>>
}
