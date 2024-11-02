package com.caretech.careconnect.Patient

import android.annotation.SuppressLint
import android.content.Intent
import android.graphics.Bitmap
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.ImageButton
import android.widget.ImageView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.bumptech.glide.Glide
import com.caretech.careconnect.R
import com.caretech.careconnect.models.Patient
import com.caretech.careconnect.models.PatientRequestInEditInformation
import com.caretech.careconnect.network.PatientService
import com.google.android.material.textfield.TextInputEditText
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


class PatientEditPersonalInformationActivity : AppCompatActivity() {
    private val PICK_IMAGE_REQUEST = 1
    private val CAMERA_REQUEST = 2
    private var isEditable = false
    private var selectedImageUri: Uri? = null
    @SuppressLint("MissingInflatedId", "CutPasteId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_patient_edit_personal_information)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        // Botón para seleccionar imagen desde la galería
        val ibFotoEdit = findViewById<ImageButton>(R.id.ibFotoEdit)
        ibFotoEdit.setOnClickListener {
            showImagePickerDialog()
        }

        // Botón para alternar la edición
        val ibToggleEdit = findViewById<ImageButton>(R.id.ibEdit)
        ibToggleEdit.setOnClickListener {
            toggleEditMode()
        }

        //BotonBack
        val btnBack = findViewById<ImageButton>(R.id.ibBackWhite)

        btnBack.setOnClickListener {
            val intent = Intent(this, PatientViewProfileActivity::class.java)
            startActivity(intent)
        }

        //BotonActualizar
        val btnActualizar = findViewById<Button>(R.id.btActualizar)

        btnActualizar.setOnClickListener {
            val etNombre = findViewById<EditText>(R.id.etNombre)
            val etApellido = findViewById<EditText>(R.id.etApellido)
            val etFechaNacimiento = findViewById<EditText>(R.id.etDate)
            val etTelefono = findViewById<EditText>(R.id.etTelefono)
            val etAltura = findViewById<TextInputEditText>(R.id.etAltura)
            val etPeso = findViewById<TextInputEditText>(R.id.etPeso)
            val etMasa = findViewById<TextInputEditText>(R.id.etMasaCorporal)
            val ibFoto = findViewById<ImageButton>(R.id.ibFotoEdit)

            // Procesa la imagen
            val foto: String? = selectedImageUri?.let { uri ->
                // Convierte la URI a una cadena base64 o gestiona la imagen según tus necesidades
                contentResolver.openInputStream(uri)?.buffered()?.use { it.readBytes() }?.let { bytes ->
                    android.util.Base64.encodeToString(bytes, android.util.Base64.DEFAULT)
                }
            }

            val patient = PatientRequestInEditInformation(
                name = etNombre?.text.toString(),
                lastname = etApellido?.text.toString(),
                birthdate = etFechaNacimiento?.text.toString(),
                phone = etTelefono?.text.toString(),
                height = etAltura.text.toString().toDoubleOrNull(),
                weight = etPeso.text.toString().toDoubleOrNull(),
                body_mass_index = etMasa.text.toString().toDoubleOrNull(),
                photo = foto ?: ""
            )

            //Instancia de Retrofit
            val retrofit = Retrofit.Builder()
                .baseUrl("https://")
                .addConverterFactory(GsonConverterFactory.create())
                .build()

            //Instancia del service
            val patientService : PatientService = retrofit.create(PatientService::class.java)

            val requestUpdate = patientService.updatePersonalInformation(patient)

            requestUpdate.enqueue(object : Callback<Patient> {
                override fun onResponse(call: Call<Patient>, response: Response<Patient>) {
                    if (response.isSuccessful) {
                        val patient = response.body()!!
                        etNombre.setText(patient.name)
                        etApellido.setText(patient.lastname)
                        etFechaNacimiento.setText(patient.birthdate.toString())
                        etTelefono.setText(patient.phone)
                        etAltura.setText(patient.height.toString())
                        etPeso.setText(patient.weight.toString())
                        etMasa.setText(patient.body_mass_index.toString())
                        ibFoto.setImageURI(Uri.parse(patient.photo))
                        //MostrarImagen
                        Glide.with(this@PatientEditPersonalInformationActivity)
                            .load(patient.photo)
                            .into(ibFoto)
                    }
                }

                override fun onFailure(call: Call<Patient>, t: Throwable) {
                    Log.e("Error", t.message.toString())
                }
            })
        }
        setEditMode(false)
    }

    private fun showImagePickerDialog() {
        val options = arrayOf("Elegir de la galería", "Tomar foto")
        val builder = AlertDialog.Builder(this)
        builder.setTitle("Seleccionar imagen")
        builder.setItems(options) { dialog, which ->
            when (which) {
                0 -> openGallery()
                1 -> openCamera()
            }
        }
        builder.show()
    }

    private fun openGallery() {
        val intent = Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
        startActivityForResult(intent, PICK_IMAGE_REQUEST)
    }

    private fun openCamera() {
        val intent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
        startActivityForResult(intent, CAMERA_REQUEST)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        //FotoEdit
        val ibFotoEdit = findViewById<ImageButton>(R.id.ibFotoEdit)
        if (resultCode == RESULT_OK) {
            when (requestCode) {
                PICK_IMAGE_REQUEST -> {
                    val selectedImageUri: Uri? = data?.data
                    ibFotoEdit.setImageURI(selectedImageUri)
                }
                CAMERA_REQUEST -> {
                    val photo: Bitmap? = data?.extras?.get("data") as? Bitmap
                    ibFotoEdit.setImageBitmap(photo)
                }
            }
        }
    }

    private fun toggleEditMode() {
        isEditable = !isEditable
        setEditMode(isEditable)
    }

    private fun setEditMode(editable: Boolean) {
        val etNombre = findViewById<EditText>(R.id.etNombre)
        val etApellido = findViewById<EditText>(R.id.etApellido)
        val etDate = findViewById<EditText>(R.id.etDate)
        val etTelefono = findViewById<EditText>(R.id.etTelefono)
        val ibFotoEdit = findViewById<ImageButton>(R.id.ibFotoEdit)
        val etAltura = findViewById<TextInputEditText>(R.id.etAltura)
        val etPeso = findViewById<TextInputEditText>(R.id.etPeso)
        val etMasa = findViewById<TextInputEditText>(R.id.etMasaCorporal)
        val ivFecha = findViewById<ImageView>(R.id.ivFecha)
        val ivTelefono = findViewById<ImageView>(R.id.ivTelefono)
        val ibToggleEdit = findViewById<ImageButton>(R.id.ibEdit)
        val color = if (editable) R.color.plomo else R.color.celeste

        etNombre.isEnabled = editable
        etApellido.isEnabled = editable
        etDate.isEnabled = editable
        etTelefono.isEnabled = editable
        ibFotoEdit.isEnabled = editable
        etAltura.isEnabled = editable
        etPeso.isEnabled = editable
        etMasa.isEnabled = editable


        ibToggleEdit.setBackgroundColor(ContextCompat.getColor(this, color))
        ivFecha.setBackgroundColor(ContextCompat.getColor(this, color))
        ivTelefono.setBackgroundColor(ContextCompat.getColor(this, color))
    }

}