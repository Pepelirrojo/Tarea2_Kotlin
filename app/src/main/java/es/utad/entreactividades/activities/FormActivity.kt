package es.utad.entreactividades.activities

import android.app.Activity
import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import es.utad.entreactividades.MainActivity
import es.utad.entreactividades.R
import es.utad.entreactividades.model.User
import org.json.JSONObject
import kotlinx.android.synthetic.main.activity_form.*

class FormActivity : AppCompatActivity() {
    var registro: Boolean = true
    var usuario = User()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_form)

        registro = intent.getBooleanExtra("registro", true)
        if (registro) {
            buttonActualizar.visibility = View.INVISIBLE
            buttonAceptar.visibility = View.VISIBLE
        }


        if (!registro) {
            var bundle: Bundle = intent.getBundleExtra("usuario")
            usuario.setBundle(bundle)

            editTextUsuario.setText(usuario.usuario)
            editTextPassword.setText(usuario.password)
            editTextNombre.setText(usuario.nombre)
            editTextApellidos.setText(usuario.apellidos)

            editTextUsuario.isEnabled = true
            editTextPassword.isEnabled = true
            editTextNombre.isEnabled = true
            editTextApellidos.isEnabled = true
            buttonActualizar.setOnClickListener { onActualizar() }


        }
    }

    fun onActualizar() {
        usuario.usuario = editTextUsuario.text.toString()
        usuario.password = editTextPassword.text.toString()
        usuario.nombre = editTextNombre.text.toString()
        usuario.apellidos = editTextApellidos.text.toString()

        var resultIntent = Intent(this, MainActivity::class.java)
        resultIntent.putExtra("usuario", usuario.getBundle())

        setResult(Activity.RESULT_OK, resultIntent)
        val json = JSONObject();

        json.put("user", usuario.usuario)
        json.put("password", usuario.password)
        json.put("name", usuario.nombre)
        json.put("surname", usuario.apellidos)

        val cadenaJson: String = json.toString()

        var nombreFichero = "ficheroInterno.json"
        var fileJson = openFileOutput(nombreFichero, Context.MODE_PRIVATE)
        fileJson.write(cadenaJson.toByteArray())
        fileJson.close()
        finish()
    }

    fun onCancelar(view: View) {
        finish()
    }

    fun onAceptar(view: View) {
        if (registro) {

            usuario.usuario = editTextUsuario.text.toString()
            usuario.password = editTextPassword.text.toString()
            usuario.nombre = editTextNombre.text.toString()
            usuario.apellidos = editTextApellidos.text.toString()

            var resultIntent = Intent(this, MainActivity::class.java)
            resultIntent.putExtra("usuario", usuario.getBundle())

            setResult(Activity.RESULT_OK, resultIntent)
            val json = JSONObject();

            json.put("user", usuario.usuario)
            json.put("password", usuario.password)
            json.put("name", usuario.nombre)
            json.put("surname", usuario.apellidos)

            val cadenaJson: String = json.toString()

            var nombreFichero = "ficheroInterno.json"
            var fileJson = openFileOutput(nombreFichero, Context.MODE_PRIVATE)
            fileJson.write(cadenaJson.toByteArray())
            fileJson.close()

            finish()
        } else {
            finish()
        }
    }
}