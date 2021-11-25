package com.example.testtask.Activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.testtask.R

class MainMenuActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportActionBar?.hide()
        setContentView(R.layout.main_menu)


    }
}