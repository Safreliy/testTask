package com.example.testtask.ViewModels

import android.util.Log
import androidx.lifecycle.ViewModel
import com.example.testtask.Interfaces.ApiInterface
import com.example.testtask.Model.*
import retrofit2.Call
import retrofit2.Response

class MainMenuModelView: ViewModel()  {
    private val apiServicePROD: ApiInterface = ApiInterface.create(VMGeneralData.PROD_URL)

    fun getDishes(){
        apiServicePROD.getDished().enqueue(object : retrofit2.Callback<DishCathegory> {
            override fun onFailure(call: Call<DishCathegory>, t: Throwable) {
                Log.d("TAG_TAG", "Failed :" + t.message)
            }

            override fun onResponse(call: Call<DishCathegory>, response: Response<DishCathegory>) {
                VMGeneralData.transactionId = response.body()?.data?.transactionId ?: ""
            }
        })
    }
}