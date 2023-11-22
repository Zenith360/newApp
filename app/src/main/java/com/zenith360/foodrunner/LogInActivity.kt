package com.zenith360.foodrunner

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast

class LogInActivity : AppCompatActivity() {
    private lateinit var imgLogo: ImageView
    private lateinit var etMob: EditText
    private lateinit var etPwd: EditText
    private lateinit var btnLogIn: Button
    private lateinit var txtForgotPassword: TextView
    private lateinit var txtRegister: TextView

    private lateinit var sharedPreferences: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        sharedPreferences =
            getSharedPreferences(getString(R.string.preference_file_name), Context.MODE_PRIVATE)

        setContentView(R.layout.activity_log_in)

        val intent = Intent(this@LogInActivity, ActualMainActivity::class.java)
        val isLoggedIn = sharedPreferences.getBoolean("Logged_In", false)

        if (isLoggedIn) {
            startActivity(intent)
            finish()
        }

        imgLogo = findViewById(R.id.imgLogo)
        etMob = findViewById(R.id.etMob)
        etPwd = findViewById(R.id.etPwd)
        btnLogIn = findViewById(R.id.btnLogIn)
        txtForgotPassword = findViewById(R.id.txtForgotPassword)
        txtRegister = findViewById(R.id.txtRegister)

        txtForgotPassword.setOnClickListener {

            val intent = Intent(this@LogInActivity, ForgotPwd::class.java)
            startActivity(intent)
        }

        txtRegister.setOnClickListener {

            val intent = Intent(this@LogInActivity, RegisterActivity::class.java)
            startActivity(intent)
        }

        btnLogIn.setOnClickListener {

            val mobNumber = etMob.text.toString()
            val pwd = etPwd.text.toString()

            val mobNumberSet = Credentials.mobNumberSet
            val pwdMap = Credentials.pwdMap

            if (mobNumberSet.contains(mobNumber) && pwdMap[mobNumber] == pwd) {

                sharedPreferences.edit().putBoolean("Logged_In", true).apply()

                val intent = Intent(this@LogInActivity, ActualMainActivity::class.java)
                startActivity(intent)
                finish()
            } else {

                Toast.makeText(
                    this@LogInActivity,
                    "Incorrect Credentials",
                    Toast.LENGTH_SHORT
                ).show()

                etPwd.text.clear()
            }
        }
    }
}