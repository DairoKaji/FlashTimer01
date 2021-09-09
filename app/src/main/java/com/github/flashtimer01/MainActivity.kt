package com.github.flashtimer01

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        button_flash.setOnClickListener {
            val txt = time_flash.text
            time_flash.text = "5:00"
        }

    }
}