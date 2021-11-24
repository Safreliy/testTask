package com.example.testtask.ViewModels

import com.example.testtask.Interfaces.ApiInterface
import android.text.Editable
import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.testtask.Model.PhoneNumber
import com.example.testtask.Model.Transaction
import com.example.testtask.Model.VMGeneralData
import com.example.testtask.Model.Wrapper
import retrofit2.Call
import retrofit2.Response


class AuthorizationViewModel: ViewModel() {
    var phoneNumber: String = ""
    private val apiService: ApiInterface = ApiInterface.create(VMGeneralData.PROD_URL)

    fun changePhone(s: Editable?){
        phoneNumber = s.toString().replace("\\s".toRegex(), "")
    }
    fun sendCode(){
        val phoneNumberObj = PhoneNumber(phoneNumber)
        apiService.sendCode(phoneNumberObj).enqueue(object : retrofit2.Callback<Wrapper<Transaction>> {
            override fun onFailure(call: Call<Wrapper<Transaction>>, t: Throwable) {
                Log.d("TAG_TAG", "Failed :" + t.message)
            }

            override fun onResponse(call: Call<Wrapper<Transaction>>, response: Response<Wrapper<Transaction>>) {
                VMGeneralData.transactionId = response.body()?.data?.transactionId ?: ""
            }
        })
        VMGeneralData.phoneNumber = phoneNumber
    }
}