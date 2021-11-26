package com.example.testtask.Activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.testtask.R
import com.example.testtask.ViewModels.MainMenuViewModel

class MainMenuActivity : AppCompatActivity() {
    private val model: MainMenuViewModel by viewModels<MainMenuViewModel>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportActionBar?.hide()
        setContentView(R.layout.main_menu)

        val recyclerViewMenu = findViewById<RecyclerView>(R.id.recyclerViewMenu)
        val adapter = model.getDishes()
        val gotData = androidx.lifecycle.Observer<Boolean>{ check:Boolean ->
            if(check){
                recyclerViewMenu.adapter = adapter
                recyclerViewMenu.layoutManager = LinearLayoutManager(this,LinearLayoutManager.VERTICAL, false)
            }
        }

        model.gotData.observe(this,gotData)
    }
}