package com.arash.altafi.swiperefresh

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.arash.altafi.swiperefresh.java.MainJava
import com.arash.altafi.swiperefresh.kotlin.MainKotlin
import com.arash.altafi.swiperefresh.kotlin1.MainKotlin1
import com.google.android.material.button.MaterialButton

class MainActivity : AppCompatActivity() {

    private lateinit var btnJava : MaterialButton
    private lateinit var btnKotlin : MaterialButton
    private lateinit var btnKotlin1 : MaterialButton

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        init()
    }

    private fun init() {
        bindViews()

        btnJava.setOnClickListener {
            startActivity(Intent(this , MainJava::class.java))
        }

        btnKotlin.setOnClickListener {
            startActivity(Intent(this , MainKotlin::class.java))
        }

        btnKotlin1.setOnClickListener {
            startActivity(Intent(this , MainKotlin1::class.java))
        }
    }

    private fun bindViews() {
        btnJava = findViewById(R.id.btn_java)
        btnKotlin = findViewById(R.id.btn_kotlin)
        btnKotlin1 = findViewById(R.id.btn_kotlin_1)
    }

}