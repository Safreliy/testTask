package com.example.testtask.Model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class coords(
    @Expose
    @SerializedName("lat")var lat: Int? = null,
    @Expose
    @SerializedName("lon")var lon: Int? = null
)
data class Address (
    @Expose
    @SerializedName("id")var id: Int? = null,
    @Expose
    @SerializedName("type")var type: String? = null,
    @Expose
    @SerializedName("name")var name: String? = null,
    @Expose
    @SerializedName("street")var street: String? = null,
    @Expose
    @SerializedName("city")var city: String? = null,
    @Expose
    @SerializedName("country")var country: String? = null,
    @Expose
    @SerializedName("coordinates")var coordinates:coords = coords()
)
data class User (
    @Expose
    @SerializedName("avatar")var avatar: String? = null,
    @Expose
    @SerializedName("name")var name: String? = null,
    @Expose
    @SerializedName("phone")var phone: String? = null,
    @Expose
    @SerializedName("sex")var sex: String? = null,
    @Expose
    @SerializedName("birthday")var birthday: Int? = null,
    @Expose
    @SerializedName("email")var email: String? = null,
    @Expose
    @SerializedName("addressesCount")var addressesCount: Int? = null,
    @Expose
    @SerializedName("primaryAddress")var primaryAddress: Address = Address(),
    @Expose
    @SerializedName("varflat")var flat:String? = null,
    @Expose
    @SerializedName("varentrance")var entrance:String? = null,
    @Expose
    @SerializedName("varfloor")var floor: String? = null,
    @Expose
    @SerializedName("varenterCode")var enterCode: String? = null,
    @Expose
    @SerializedName("varcomment")var comment: String? = null
)
data class UserData (
    @Expose
    @SerializedName("validated")var avatar: String? = null,
    @Expose
    @SerializedName("token")var name: String? = null,
    @Expose
    @SerializedName("user")var user: User? = null,
)

data class PhoneNumber(
    @Expose
    @SerializedName("phone") var phone: String? = null
)

data class TransactionCode(
    @Expose
    @SerializedName("transactionId") var transactionId: String?,
    @Expose
    @SerializedName("code") var code:String?,
    @Expose
    @SerializedName("onesignal") var onesignal: String? = ""
)
data class Transaction(
    @Expose
    @SerializedName("transactionId") var transactionId: String?
)
