package com.example.testtask.ViewModels

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.testtask.Adapters.PositionsRecyclerAdapter
import com.example.testtask.Interfaces.ApiInterface
import com.example.testtask.Model.*
import retrofit2.Call
import retrofit2.Response

class MainMenuViewModel: ViewModel()  {
    private val apiServicePROD: ApiInterface = ApiInterface.create(VMGeneralData.PROD_URL)
    private var categories: List<DishData>? = null
    private var categoryNames: Array<String>? = null
    private var positionArr: Array<Position?> = arrayOfNulls(1000)
    var gotData: MutableLiveData<Boolean> = MutableLiveData(false)

    fun getAdapter() : PositionsRecyclerAdapter{
        return PositionsRecyclerAdapter(positionArr, categoryNames)
    }
    fun getDishes() {
        apiServicePROD.getDished().enqueue(object : retrofit2.Callback<DishCathegory> {
            override fun onFailure(call: Call<DishCathegory>, t: Throwable) {
                Log.d("TAG_TAG", "Failed :" + t.message)
            }

            override fun onResponse(call: Call<DishCathegory>, response: Response<DishCathegory>) {
                categories = response.body()?.data?.menu
                insertDishes()
                gotData.value = true
            }
        })
    }

    fun insertDishes(){
        //формируем массив блюд с указанными для них категориями
        val sizeCat = categories?.size ?: 0
        for (i in 0 until sizeCat){
            categoryNames?.set(i, categories?.get(i)?.name ?: "")
        }
        for (i in 0 until sizeCat) {
            var currentCategory = categories?.get(i)?.positions
            for(j in 0 until (currentCategory?.size ?: 0)) {
                var currentDish = currentCategory?.get(j)
                currentDish?.category = i
                currentDish?.dishId = j
                positionArr[j + i*(currentCategory?.size?: 0)] = currentDish
            }
        }
    }
}