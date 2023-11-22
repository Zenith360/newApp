package com.zenith360.foodrunner

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView

class GetInfo : AppCompatActivity() {

    private lateinit var txtNameField : TextView
    private lateinit var txtEmailField : TextView
    private lateinit var txtMobNumField : TextView
    private lateinit var txtPwdField : TextView
    private lateinit var txtAddrField : TextView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_get_info)

        val mobNum = intent.getStringExtra("mobNum")

        txtNameField = findViewById(R.id.txtNameField)
        txtEmailField = findViewById(R.id.txtEmailField)
        txtMobNumField = findViewById(R.id.txtMobNumField)
        txtPwdField = findViewById(R.id.txtPwdField)
        txtAddrField = findViewById(R.id.txtAddrField)

        txtNameField.text = Credentials.nameMap[mobNum]
        txtEmailField.text = Credentials.emailMap[mobNum]
        txtMobNumField.text = mobNum
        txtPwdField.text = Credentials.pwdMap[mobNum]
        txtAddrField.text = Credentials.addrMap[mobNum]
    }

    override fun onPause() {
        super.onPause()
        finish()
    }
}