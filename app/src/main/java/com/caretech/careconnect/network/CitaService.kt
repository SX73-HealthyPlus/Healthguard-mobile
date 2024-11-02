package com.caretech.careconnect.network

import com.caretech.careconnect.models.Doctor
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface CitaService {
    @GET("")
    fun searchDoctor(@Query("") fetchAll: String): Call<List<Doctor>>

}