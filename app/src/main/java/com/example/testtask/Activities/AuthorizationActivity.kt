package com.example.testtask.Activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.widget.Button
import android.widget.EditText
import androidx.activity.viewModels
import com.airbnb.paris.Paris
import com.example.testtask.ViewModels.AuthorizationViewModel
import com.example.testtask.R
import ru.tinkoff.decoro.MaskImpl

import ru.tinkoff.decoro.watchers.MaskFormatWatcher

import ru.tinkoff.decoro.watchers.FormatWatcher

import ru.tinkoff.decoro.parser.UnderscoreDigitSlotsParser


class AuthorizationActivity : AppCompatActivity() {

    private val model: AuthorizationViewModel by viewModels<AuthorizationViewModel>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportActionBar?.hide()
        setContentView(R.layout.activity_authorization)

        //Маска для номера телефона
        var phoneNumberView = findViewById<EditText>(R.id.editTextPhone)
        val slots = UnderscoreDigitSlotsParser().parseSlots("___ ___ __ __")
        val formatWatcher: FormatWatcher = MaskFormatWatcher( // форматироваyние текста
            MaskImpl.createTerminated(slots)
        )
        formatWatcher.installOn(phoneNumberView)

        var confirmButton: Button = findViewById(R.id.toCardBtn)

        phoneNumberView.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
                if(s.toString().replace("\\s".toRegex(), "").length == 10){
                    Paris.style(confirmButton).apply(R.style.buttonGreen)
                }
                else{
                    Paris.style(confirmButton).apply(R.style.buttonGrey)
                }
                model.changePhone(s)
            }
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
            }
        })
    }
    fun onConfirmBtnClick(view:View){
        if(model.phoneNumber.length == 10) {
            model.sendCode()
            val intent = Intent(this, SendCodeActivity::class.java)
            startActivity(intent)
        }
    }
    fun onSkipButtonClick(view: View){
        val intent = Intent(this, MainMenuActivity::class.java)
        startActivity(intent)
    }
}
