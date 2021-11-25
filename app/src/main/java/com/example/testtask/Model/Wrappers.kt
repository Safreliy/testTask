package com.example.testtask.Model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class Wrapper<T>(
    @Expose
    @SerializedName("data") var data:T,
    @Expose
    @SerializedName("success") var success:Boolean?
)