package com.zenith360.foodrunner

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class ActualMainActivity : AppCompatActivity() {

    private lateinit var btnFinalBtn : Button

    private lateinit var sharedPreferences: SharedPreferences
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        sharedPreferences = getSharedPreferences(getString(R.string.preference_file_name), Context.MODE_PRIVATE)

        setContentView(R.layout.activity_actual_main)

        btnFinalBtn = findViewById(R.id.btnFinalBtn)

        btnFinalBtn.setOnClickListener{

            val intent = Intent(this@ActualMainActivity, LogInActivity::class.java)
            sharedPreferences.edit().clear().apply()
            startActivity(intent)
        }
    }
}