package com.example.testtask.Activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.activity.viewModels
import com.airbnb.paris.Paris
import com.example.testtask.R
import com.example.testtask.ViewModels.SendCodeViewModel



class SendCodeActivity : AppCompatActivity() {
    private val model: SendCodeViewModel by viewModels<SendCodeViewModel>()
    private lateinit var timerView: TextView
    private lateinit var resendCodeButton : Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportActionBar?.hide()
        setContentView(R.layout.activity_send_code)

        val codeView = findViewById<EditText>(R.id.editCode)
        val continueButton : Button = findViewById(R.id.continueBtn)
        resendCodeButton = findViewById(R.id.resendBtn)
        resendCodeButton.visibility = View.GONE
        timerView = findViewById(R.id.timer)

        model.startTimer()
        val timerTickObserver = androidx.lifecycle.Observer<Long>{ newNumber:Long ->
            timerView.text = timerView.text.replace("\\d{1,2}".toRegex(),newNumber.toString())
        }
        val timerFinishObserver = androidx.lifecycle.Observer<Boolean>{ check:Boolean ->
            if(check) {
                resendCodeButton.visibility = View.VISIBLE
                timerView.text = resources.getText(R.string.resend_code_allow)
            }
        }
        val gotAccess = androidx.lifecycle.Observer<Boolean>{ check:Boolean ->
            if(check) {
                val intent = Intent(this, MainMenuActivity::class.java)
                startActivity(intent)
            }
        }
        model.tickCounter.observe(this, timerTickObserver)
        model.timerFinished.observe(this,timerFinishObserver)
        model.flagAccess.observe(this,gotAccess)
        codeView.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
                if(s.toString().replace("\\s".toRegex(), "").length == 6){
                    Paris.style(continueButton).apply(R.style.buttonGreen)
                }
                else{
                    Paris.style(continueButton).apply(R.style.buttonGrey)
                }
                model.changeCode(s)
            }
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
            }
        })
    }
    fun onContinueBtnClick(view: View){
        if(model.code.length == 6) {
            model.checkCode()
        }
    }

    fun onResendBtnClick(view: View){
        if(model.timerFinished.value ?: false){
            timerView.text = resources.getText(R.string.resend_code)
            model.resendCode()
            resendCodeButton.visibility = View.GONE
        }
    }
}