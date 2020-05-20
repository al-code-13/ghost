package com.example.nami

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.example.nami.presenters.LoginPresenter
import com.example.nami.presenters.LoginUI
import kotlinx.android.synthetic.main.activity_login.*

class Login : AppCompatActivity(), LoginUI {
    val presenter = LoginPresenter(this)
    override fun onCreate(savedInstanceState: Bundle?) {
        Thread.sleep(2000)
        setTheme(R.style.SplashTheme)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

    }

    fun login(v: View) {
        presenter.actionLogin(edit_user.text.toString(),edit_password.text.toString())
    }

    override fun showHome() {
        val intent = Intent(this, MainActivity::class.java)
        ContextCompat.startActivity(this, intent, null)
    }

    override fun showError(error: String) {
        runOnUiThread {
            Toast.makeText(applicationContext,error,Toast.LENGTH_LONG).show()
        }
    }
}
