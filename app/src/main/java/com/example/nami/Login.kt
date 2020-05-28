package com.example.nami

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.example.nami.presenters.LoginPresenter
import com.example.nami.presenters.LoginUI
import kotlinx.android.synthetic.main.activity_login.*
import kotlinx.coroutines.*

class Login : AppCompatActivity(), LoginUI {
    private var viewModelJob: Job = Job()
    private val presenter = LoginPresenter(this)
    private val uiScope = CoroutineScope(Dispatchers.Main + viewModelJob)
    override fun onCreate(savedInstanceState: Bundle?) {
        Thread.sleep(2000)
        setTheme(R.style.SplashTheme)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

    }

     fun login(v: View) {
        uiScope.launch {
            logins(v)
        }
    }

     suspend fun logins(v: View) {
         withContext(Dispatchers.IO){
             presenter.actionLogin(edit_user.text.toString(), edit_password.text.toString())
         }

    }

    override fun showHome(token: String) {
        val sharedPreference = getSharedPreferences("localStorage", Context.MODE_PRIVATE)
        var editor = sharedPreference.edit()
        editor.putString("token", token)
        editor.commit()
        Log.i("TOKENCITO", sharedPreference.getString("token", "localStorage"))
        val intent = Intent(this, MainActivity::class.java)
        ContextCompat.startActivity(this, intent, null)

    }


    override fun showError(error: String) {
        runOnUiThread {
            Toast.makeText(applicationContext, error, Toast.LENGTH_LONG).show()
        }
    }
}
