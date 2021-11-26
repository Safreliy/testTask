package com.example.testtask.ViewModels

import android.util.Log
import androidx.lifecycle.ViewModel
import com.example.testtask.Interfaces.ApiInterface
import com.example.testtask.Model.*
import retrofit2.Call
import retrofit2.Response

class MainMenuViewModel: ViewModel()  {
    private val apiServicePROD: ApiInterface = ApiInterface.create(VMGeneralData.PROD_URL)
    private var categories: List<DishData>? = null
    private var categoryNames: Array<String>? = null
    private var groupData: Array<Int>? = null
    fun getDishes(){
        apiServicePROD.getDished().enqueue(object : retrofit2.Callback<DishCathegory> {
            override fun onFailure(call: Call<DishCathegory>, t: Throwable) {
                Log.d("TAG_TAG", "Failed :" + t.message)
            }

            override fun onResponse(call: Call<DishCathegory>, response: Response<DishCathegory>) {
                categories = response.body()?.data
            }
        })
    }
    fun insertDishes(){
        val sizeCat = categories?.size ?: 0
        for (i in 0 until sizeCat){
            categoryNames?.set(i, categories?.get(i)?.name ?: "")
        }
        for (i in 0 until sizeCat) {
            val menu: Menu = menuList.get(i)
            if (groupData.size() === 0) {
                groupData.add(c, j)
                c++
                j++
            } else {
                if (menu.getCategoryname()
                        .equalsIgnoreCase(menuList.get(i - 1).getCategoryname())
                ) {
                    groupData.add(c, j)
                    c++
                    j++
                } else {
                    j = 0
                    groupData.add(c, j)
                    c++
                    j++
                }
            }
        }
    }
}