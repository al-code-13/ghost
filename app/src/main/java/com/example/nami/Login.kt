package com.example.nami

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.ProgressBar
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.example.nami.presenters.LoginPresenter
import com.example.nami.presenters.LoginUI
import kotlinx.android.synthetic.main.activity_login.*
import kotlinx.coroutines.*


class Login : AppCompatActivity(), LoginUI {
    private var viewModelJob: Job = Job()
    var spinner: ProgressBar? = null

    private val presenter = LoginPresenter(this)
    private val uiScope = CoroutineScope(Dispatchers.Main + viewModelJob)
    override fun onCreate(savedInstanceState: Bundle?) {
        Thread.sleep(2000)
        setTheme(R.style.SplashTheme)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        spinner = findViewById(R.id.progressBar)
        spinner?.visibility = View.GONE
    }


    fun login(v: View) {

        Log.i("ELTHREAD", Thread.currentThread().name)
        spinner?.visibility = View.VISIBLE
        presenter.actionLogin(edit_user.text.toString(), edit_password.text.toString())
        spinner?.visibility = View.GONE


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
            spinner?.visibility = View.GONE
            Toast.makeText(applicationContext, error, Toast.LENGTH_LONG).show()
        }
    }

    override fun showLoad() {
        runOnUiThread {
            spinner?.visibility = View.VISIBLE
        }
    }
}
