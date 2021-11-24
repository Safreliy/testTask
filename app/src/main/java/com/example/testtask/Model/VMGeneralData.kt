package com.example.testtask.Model

import com.example.testtask.Interfaces.ApiInterface

class VMGeneralData {
    companion object {
        var User: UserData? = UserData()
        var transactionId: String = ""
        var phoneNumber: String = ""
        val PROD_URL = "https://prod-api.gurmanika.ru/api/"
        val DEV_URL = "https://dev-api.gurmanika.ru/api/"
    }
}