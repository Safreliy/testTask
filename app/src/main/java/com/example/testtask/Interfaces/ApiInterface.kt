package com.example.testtask.Interfaces
import com.example.testtask.Model.*
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.Body
import retrofit2.http.POST
import retrofit2.http.Url

interface ApiInterface {


    @POST("phone/check")
    fun sendCode(@Body phone:PhoneNumber) : Call<Wrapper<Transaction>>

    @POST("phone/verify")
    fun checkCode(@Body transactionCode: TransactionCode) : Call<Wrapper<UserData>>
    companion object {


        fun create(BASE_URL: String) : ApiInterface {
            val httpLogginInterceptor = HttpLoggingInterceptor()
            httpLogginInterceptor.level = HttpLoggingInterceptor.Level.BODY
            val okHttpClient = OkHttpClient.Builder()
                .addInterceptor(httpLogginInterceptor)
                .build()

            val retrofit = Retrofit.Builder()
                .baseUrl(BASE_URL)
                .client(okHttpClient)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build()
            return retrofit.create(ApiInterface::class.java)

        }
    }
}