package com.zenith360.foodrunner

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast

class ForgotPwd : AppCompatActivity() {

    private lateinit var imgLogopwd: ImageView
    private lateinit var txtFpwd: TextView
    private lateinit var etEmailpwd: EditText
    private lateinit var etMobNumfpwd: EditText
    private lateinit var btnFpwd: Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_forgot_pwd)

        imgLogopwd = findViewById(R.id.imgLogopwd)
        txtFpwd = findViewById(R.id.txtFpwd)
        etEmailpwd = findViewById(R.id.etEmailpwd)
        etMobNumfpwd = findViewById(R.id.etMobNumfpwd)
        btnFpwd = findViewById(R.id.btnFpwd)

        btnFpwd.setOnClickListener {

            val mobNum = etMobNumfpwd.text.toString()

            if (Credentials.mobNumberSet.contains(mobNum)) {

                val intent = Intent(this@ForgotPwd, GetInfo::class.java)
                intent.putExtra("mobNum", mobNum)
                startActivity(intent)
                finish()
            } else {

                Toast.makeText(
                    this@ForgotPwd,
                    "There is no info. associated with this number!",
                    Toast.LENGTH_LONG
                ).show()

                etEmailpwd.text.clear()
                etMobNumfpwd.text.clear()
            }
        }
    }
}