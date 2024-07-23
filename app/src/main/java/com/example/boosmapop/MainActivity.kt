package com.example.boosmapop

import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.speech.RecognizerIntent
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    private lateinit var txtOutput: TextView
    private lateinit var btnSpeak: Button
    private lateinit var btnSave: Button
    private lateinit var btnEdit: Button
    private lateinit var etName: EditText
    private lateinit var etPhoneNumber: EditText
    private lateinit var speechLauncher: ActivityResultLauncher<Intent>
    private val speechResults = mutableListOf<String>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        txtOutput = findViewById(R.id.txtOutput)
        btnSpeak = findViewById(R.id.btnSpeak)
        btnSave = findViewById(R.id.btnSave)
        btnEdit = findViewById(R.id.btnEdit)
        etName = findViewById(R.id.etName)
        etPhoneNumber = findViewById(R.id.etPhoneNumber)

        // Cargar los datos guardados
        loadData()

        // Registrar el ActivityResultLauncher para el reconocimiento de voz
        speechLauncher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
            if (result.resultCode == RESULT_OK && result.data != null) {
                val speechResult = result.data?.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS)
                if (!speechResult.isNullOrEmpty()) {
                    val input = speechResult[0].replace("\\s+".toRegex(), "")
                    splitIntoChunksOfNine(input)
                    updateOutputText()
                }
            }
        }

        btnSpeak.setOnClickListener {
            speechResults.clear() // Limpiar resultados anteriores
            startVoiceInput()
        }

        btnSave.setOnClickListener {
            saveData()
            setInputsEnabled(false)
        }

        btnEdit.setOnClickListener { setInputsEnabled(true) }
    }

    private fun startVoiceInput() {
        val intent = Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH).apply {
            putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL, RecognizerIntent.LANGUAGE_MODEL_FREE_FORM)
            putExtra(RecognizerIntent.EXTRA_LANGUAGE, "es-ES")
            putExtra(RecognizerIntent.EXTRA_PROMPT, "Di una serie de números")
        }
        speechLauncher.launch(intent)
    }

    private fun splitIntoChunksOfNine(input: String) {
        val regex = ".{1,9}".toRegex()
        regex.findAll(input).forEach {
            speechResults.add(it.value)
        }
    }

    private fun updateOutputText() {
        val name = etName.text.toString()
        val finalText = speechResults.joinToString(separator = "\n") + "\n$name"
        txtOutput.text = finalText
//        sendToWhatsApp(finalText)
        copyToClipboard(finalText)
    }
    private fun copyToClipboard(text: String) {
        val clipboard = getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager
        val clip = ClipData.newPlainText("label", text)
        clipboard.setPrimaryClip(clip)
        Toast.makeText(this, "Texto copiado al portapapeles", Toast.LENGTH_SHORT).show()
    }

//    private fun sendToWhatsApp(message: String) {
//        val phoneNumber = etPhoneNumber.text.toString()
//        if (phoneNumber.isNotEmpty()) {
//            val url = "https://api.whatsapp.com/send?phone=$phoneNumber&text=${Uri.encode(message)}"
//            val sendIntent = Intent(Intent.ACTION_VIEW).apply {
//                data = Uri.parse(url)
//            }
//            try {
//                startActivity(sendIntent)
//            } catch (e: ActivityNotFoundException) {
//                Toast.makeText(this, "WhatsApp no está instalado", Toast.LENGTH_SHORT).show()
//            }
//        } else {
//            Toast.makeText(this, "Por favor, ingrese un número de WhatsApp", Toast.LENGTH_SHORT).show()
//        }
//    }

    private fun saveData() {
        val sharedPreferences = getSharedPreferences("MyAppPrefs", Context.MODE_PRIVATE)
        with(sharedPreferences.edit()) {
            putString("name", etName.text.toString())
            putString("phoneNumber", etPhoneNumber.text.toString())
            apply()
        }
        Toast.makeText(this, "Datos guardados", Toast.LENGTH_SHORT).show()
    }

    private fun loadData() {
        val sharedPreferences = getSharedPreferences("MyAppPrefs", Context.MODE_PRIVATE)
        etName.setText(sharedPreferences.getString("name", ""))
        etPhoneNumber.setText(sharedPreferences.getString("phoneNumber", ""))
    }

    private fun setInputsEnabled(enabled: Boolean) {
        etName.isEnabled = enabled
        etPhoneNumber.isEnabled = enabled
    }
}
