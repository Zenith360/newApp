package com.zenith360.foodrunner

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast

class RegisterActivity : AppCompatActivity() {

    private lateinit var txtRegYou: TextView
    private lateinit var etName: EditText
    private lateinit var etEmail: EditText
    private lateinit var etMobileNum: EditText
    private lateinit var etAddr: EditText
    private lateinit var etPwd1: EditText
    private lateinit var etPwd2: EditText
    private lateinit var btnReg: Button

    private lateinit var sharedPreferences: SharedPreferences
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        sharedPreferences =
            getSharedPreferences(getString(R.string.preference_file_name), Context.MODE_PRIVATE)

        setContentView(R.layout.activity_register)

        txtRegYou = findViewById(R.id.txtRegYou)
        etName = findViewById(R.id.etName)
        etEmail = findViewById(R.id.etEmail)
        etMobileNum = findViewById(R.id.etMobileNum)
        etAddr = findViewById(R.id.etAddr)
        etPwd1 = findViewById(R.id.etPwd1)
        etPwd2 = findViewById(R.id.etPwd2)
        btnReg = findViewById(R.id.btnReg)

        btnReg.setOnClickListener {

            val name = etName.text.toString()
            val email = etEmail.text.toString()
            val mobNum = etMobileNum.text.toString()
            val addr = etAddr.text.toString()
            val pwd1 = etPwd1.text.toString()
            val pwd2 = etPwd2.text.toString()

            if (name.length < 3) {

                Toast.makeText(
                    this@RegisterActivity,
                    "Your Name is shorter than our min limit!",
                    Toast.LENGTH_LONG
                ).show()

                etName.text.clear()
            }

            if (pwd1 == pwd2) {

                if (pwd1.length < 4) {

                    Toast.makeText(
                        this@RegisterActivity,
                        "Your Password is shorter than our min limit!",
                        Toast.LENGTH_LONG
                    ).show()

                    etPwd1.text.clear()
                    etPwd2.text.clear()
                } else if (Credentials.mobNumberSet.contains(mobNum)) {

                    Toast.makeText(
                        this@RegisterActivity,
                        "This Number has already been registered",
                        Toast.LENGTH_SHORT
                    ).show()

                    etMobileNum.text.clear()
                } else {

                    Toast.makeText(
                        this@RegisterActivity,
                        "You Were Registered Successfully!",
                        Toast.LENGTH_SHORT
                    ).show()

                    Credentials.nameMap[mobNum] = name
                    Credentials.emailMap[mobNum] = email
                    Credentials.pwdMap[mobNum] = pwd1
                    Credentials.addrMap[mobNum] = addr
                    Credentials.mobNumberSet.add(mobNum)

                    sharedPreferences.edit().putBoolean("Logged_In", true).apply()

                    val intent = Intent(this@RegisterActivity)
                    startActivity(intent)
                    finish()
                }
            } else {

                Toast.makeText(
                    this@RegisterActivity,
                    "Please Enter Password Again",
                    Toast.LENGTH_LONG
                ).show()

                etPwd1.text.clear()
                etPwd2.text.clear()
            }
        }
    }
}