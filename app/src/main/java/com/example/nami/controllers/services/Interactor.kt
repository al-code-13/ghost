package com.example.nami.controllers.services

import android.util.Log
import com.example.nami.models.auth.LoginRequest
import com.example.nami.models.auth.LoginResponse
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import okhttp3.Call
import okhttp3.Callback
import okhttp3.Response
import java.io.IOException


class ServiceInteractor : ServiceFactory() {

    fun postLogin(
        user: String,
        password: String,
        then: (LoginResponse) -> Unit,
        error: (String) -> Unit
    ) {
        val url = serverUrl + routeBase + routeAuth + routeLogin
        val request = LoginRequest(user, password)
        val json = Gson().toJson(request)
        post(url, json).enqueue(object : Callback {

            override fun onResponse(call: Call, response: Response) {
                val body = response.body?.string()

                val gson = GsonBuilder().create()
                val res = gson.fromJson(body, LoginResponse::class.java)
                if (response.isSuccessful) {
                    then(res)
                } else {
                    error(res.message.toString())
                    //Log.i("respuesta",response.message)
                }
            }

            override fun onFailure(call: Call, e: IOException) {
                Log.i("Error", e.message.toString())
                error("Error en el servicio")
            }
        })
    }

    fun getSections(
        then: (LoginResponse) -> Unit,
        error: (String) -> Unit
    ) {

        val url = serverUrl + routeBase + routePicker +routeSections
        get(url).enqueue(object :Callback{
            override fun onResponse(call: Call, response: Response) {

                val body = response.body?.string()

                val gson = GsonBuilder().create()
                val res = gson.fromJson(body, LoginResponse::class.java)
                if (response.isSuccessful) {
                    then(res)
                } else {
                    error(res.message.toString())
                    //Log.i("respuesta",response.message)
                }
            }

            override fun onFailure(call: Call, e: IOException) {
                Log.i("Error", e.message.toString())
                error("Error en el servicio")
            }
        })
    }
}