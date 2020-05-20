package com.example.nami.controllers.services

import okhttp3.Call
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.RequestBody
import okhttp3.RequestBody.Companion.toRequestBody
import java.io.IOException

open class ServiceFactory {

    val JSON = "application/json; charset=utf-8".toMediaType()

    val serverUrl: String = "https://d1-picking-test.chefmenu.com.co"
    val routeBase: String = "/api/v1"
    val routeAuth: String = "/auth"
    val routePicker: String = "/picker"
    val routeLogin: String = "/login"
    val routeSections: String = "/sections"

    val client: OkHttpClient = OkHttpClient().newBuilder().build()


    init {
    }

    @Throws(IOException::class)
    fun get(url: String,token:String): Call {
        val request: Request = Request.Builder()
            .url(url)
            .addHeader(token)
            .build()
        //client!!.newCall(request).execute().use { response -> return response.body!!.string() }

        return client.newCall(request)
    }

    @Throws(IOException::class)
    fun post(url: String, json: String): Call {
        val body = json.toRequestBody(JSON)
        val request: Request = Request.Builder()
            .url(url)
            .post(body)
            .build()
        //client!!.newCall(request).execute().use { response -> return response.body!!.string() }

        return client.newCall(request)
    }

}