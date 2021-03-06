package com.example.testtask.Model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class Position (
    @Expose
    @SerializedName("name")
    var name: String,
    @Expose
    @SerializedName("cost")
    var cost: Int,
    @Expose
    @SerializedName("image")
    var image: String,
    @Expose
    @SerializedName("background")
    var background: String,

    var category: Int? = null,
    var dishId: Int? = null
)
data class DishData(
    @Expose
    @SerializedName("id")
    var id: String? = null,
    @Expose
    @SerializedName("name")
    var name: String? = null,
    @Expose
    @SerializedName("tint")
    var tint: String? = null,
    @Expose
    @SerializedName("positions")
    var positions: List<Position>? = null,
)
data class DishCathegory(
    @Expose
    @SerializedName("data") var data:menuWrapper,
    @Expose
    @SerializedName("success") var success:Boolean?
)

data class menuWrapper(
    @Expose
    @SerializedName("menu")
    var menu: List<DishData>
)