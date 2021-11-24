package com.example.testtask.ViewModels

import android.os.CountDownTimer
import com.example.testtask.Interfaces.ApiInterface
import android.text.Editable
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.testtask.Model.*
import com.google.gson.Gson
import org.json.JSONObject
import retrofit2.Call
import retrofit2.Response


class SendCodeViewModel: ViewModel() {
    private var phoneNumber: String = VMGeneralData.phoneNumber
    private val apiServiceDEV: ApiInterface = ApiInterface.create(VMGeneralData.DEV_URL)
    private val apiServicePROD: ApiInterface = ApiInterface.create(VMGeneralData.PROD_URL)
    var tickCounter: MutableLiveData<Long> = MutableLiveData(60)
    var timerFinished: MutableLiveData<Boolean> = MutableLiveData(false)
    var code: String = ""
    var flagAccess: MutableLiveData<Boolean> = MutableLiveData(false)

    fun changeCode(s: Editable?){
        code = s.toString().replace("\\s".toRegex(), "")
    }

    fun startTimer(){
        object : CountDownTimer(60000, 1000) {

            override fun onTick(millisUntilFinished: Long) {
                tickCounter.value = millisUntilFinished / 1000
            }

            override fun onFinish() {
                timerFinished.value = true
            }
        }.start()
    }
    fun checkCode() {
        var transactionCode = TransactionCode(VMGeneralData.transactionId, code)
        apiServicePROD.checkCode(transactionCode).enqueue(object : retrofit2.Callback<Wrapper<UserData>> {
            override fun onFailure(call: Call<Wrapper<UserData>>, t: Throwable) {
                Log.d("TAG_TAG", "Failed :" + t.message)
            }

            override fun onResponse(call: Call<Wrapper<UserData>>, response: Response<Wrapper<UserData>>) {
                VMGeneralData.User = response.body()?.data
                if(response.body()?.success ?: false){
                    flagAccess.value = true
                }
            }
        })
    }
    fun resendCode(){
        val phoneNumberObj = PhoneNumber(phoneNumber)
        apiServicePROD.sendCode(phoneNumberObj).enqueue(object : retrofit2.Callback<Wrapper<Transaction>> {
            override fun onFailure(call: Call<Wrapper<Transaction>>, t: Throwable) {
                Log.d("TAG_TAG", "Failed :" + t.message)
            }

            override fun onResponse(call: Call<Wrapper<Transaction>>, response: Response<Wrapper<Transaction>>) {
                VMGeneralData.transactionId = response.body()?.data?.transactionId ?: ""
            }
        })
        timerFinished.value = false
        startTimer()
    }
}