package com.caretech.careconnect.network

import com.caretech.careconnect.models.Doctor
import com.caretech.careconnect.models.DoctorLogin
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface DoctorService {

    @GET("doctors")
    fun getDoctors(): Call<List<Doctor>>

    @POST("doctors/login")
    fun loginDoctor(@Body doctor: DoctorLogin): Call<Doctor>
}