package com.caretech.careconnect.models

import java.io.Serializable
import java.time.LocalDate

data class Patient(
    val id: Int,
    val name: String?,
    val lastname: String?,
    val age: Int?,
    val email: String?,
    val password: String?,
    val height: Double?,
    val weight: Double?,
    val body_mass_index: Double?,
    val photo: String?,
    val phone: String?,
    val birthdate: LocalDate?,

):Serializable


data class PatientRequestInEditInformation(
    val name: String?,
    val lastname: String?,
    val birthdate: String?,
    val phone: String?,
    val height: Double?,
    val weight: Double?,
    val body_mass_index: Double?,
    val photo: String?
)

data class PatientLogin(
    val id: Int,
    val name: String?,
    val lastname: String?,
    val age: Int?,
    val email: String?,
    val password: String?,
    val height: Double?,
    val weight: Double?,
    val body_mass_index: Double?,
)
