package com.caretech.careconnect.models

import java.io.Serializable

data class Doctor(
    val id: Int,
    val name: String,
    val lastname: String,
    val specialty: String,
    val email: String,
    val password: String,
    val height: Double,
    val weight: Double,
    val bodyMassIndex: Double,
    val photo: String,
    val qualification: Double,
    val appointmentPrice: Double
):Serializable

data class DoctorLogin(
    val id: Int,
    val name: String?,
    val lastname: String?,
    val specialty: String,
    val email: String?,
    val password: String?,
    val height: Double?,
    val weight: Double?,
    val bodyMassIndex: Double?,
)
