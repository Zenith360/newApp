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

    private lateinit var txtRegYou1: TextView
    private lateinit var etName1: EditText
    private lateinit var etEmail1: EditText
    private lateinit var etMobile1: EditText
    private lateinit var etAddrchanged: EditText
    private lateinit var etPwd11: EditText
    private lateinit var etPwd21: EditText
    private lateinit var btnReg: Button

    private lateinit var sharedPreferences: SharedPreferences
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        sharedPreferences =
            getSharedPreferences(getString(R.string.preference_file_name), Context.MODE_PRIVATE)

        setContentView(R.layout.activity_register)

        txtRegYou1 = findViewById(R.id.txtRegYou1)
        etName1 = findViewById(R.id.etName1)
        etEmail1 = findViewById(R.id.etEmail1)
        etMobile1 = findViewById(R.id.etMobile1)
        etAddrchanged = findViewById(R.id.etAddrchanged)
        etPwd11 = findViewById(R.id.etPwd11)
        etPwd21 = findViewById(R.id.etPwd21)
        btnReg = findViewById(R.id.btnReg)

        btnReg.setOnClickListener {

            val name = etName1.text.toString()
            val email = etEmail1.text.toString()
            val mobNum = etMobile1.text.toString()
            val addr = etAddrchanged.text.toString()
            val pwd1 = etPwd11.text.toString()
            val pwd2 = etPwd21.text.toString()

            if (name.length < 3) {

                Toast.makeText(
                    this@RegisterActivity,
                    "Your Name is shorter than our min limit!",
                    Toast.LENGTH_LONG
                ).show()

                etName1.text.clear()
            }

            if (pwd1 == pwd2) {

                if (pwd1.length < 4) {

                    Toast.makeText(
                        this@RegisterActivity,
                        "Your Password is shorter than our min limit!",
                        Toast.LENGTH_LONG
                    ).show()

                    etPwd11.text.clear()
                    etPwd21.text.clear()
                } else if (Credentials.mobNumberSet.contains(mobNum)) {

                    Toast.makeText(
                        this@RegisterActivity,
                        "This Number has already been registered",
                        Toast.LENGTH_SHORT
                    ).show()

                    etMobile1.text.clear()
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

                    val intent = Intent(this@RegisterActivity, ActualMainActivity::class.java)
                    startActivity(intent)
                    finish()
                }
            } else {

                Toast.makeText(
                    this@RegisterActivity,
                    "Please Enter Password Again",
                    Toast.LENGTH_LONG
                ).show()

                etPwd11.text.clear()
                etPwd21.text.clear()
            }
        }
    }
}