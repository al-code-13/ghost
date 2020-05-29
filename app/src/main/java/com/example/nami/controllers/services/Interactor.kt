package com.example.nami.controllers.services

import SectionFragment
import android.util.Log
import com.example.nami.models.auth.LoginRequest
import com.example.nami.models.auth.LoginResponse
import com.example.nami.models.detailModels.*
import com.example.nami.models.sections.SectionsResponse
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import kotlinx.coroutines.*
import okhttp3.Call
import okhttp3.Callback
import okhttp3.Response
import java.io.IOException


class ServiceInteractor : ServiceFactory() {

    private var viewModelJob: Job = Job()
    private val uiScope = CoroutineScope(Dispatchers.Main + viewModelJob)
    fun postLogin(
        user: String,
        password: String,
        then: (LoginResponse) -> Unit,
        error: (String) -> Unit
    ) {
        uiScope.launch {
            postLogins(user, password, then, error)
        }
    }

    private suspend fun postLogins(
        user: String,
        password: String,
        then: (LoginResponse) -> Unit,
        error: (String) -> Unit
    ) {
        val url = serverUrl + routeBase + routeAuth + routeLogin
        val request = LoginRequest(user, password)
        val json = Gson().toJson(request)
        withContext(Dispatchers.IO){
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

    }
    fun getSections(
        token: String,
        then: (SectionsResponse) -> Unit,
        error: (String) -> Unit
    ) {

        val url = serverUrl + routeBase + routeSections
        get(url, token).enqueue(object : Callback {
            override fun onResponse(call: Call, response: Response) {

                val body = response.body?.string()

                val gson = GsonBuilder().create()
                val res = gson.fromJson(body, SectionsResponse::class.java)
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


    fun getSection(
        token: String,
        section: Long,
        then: (SectionFragment) -> Unit,
        error: (String) -> Unit
    ) {

        val url = "$serverUrl$routeBase$routeSections/$section"

        get(url, token).enqueue(object : Callback {
            override fun onResponse(call: Call, response: Response) {

                val body = response.body?.string()
                Log.i("info sin parsear",body.toString())
                val gson = GsonBuilder().create()
                val res = gson.fromJson(body, SectionFragment::class.java)
                if (response.isSuccessful) {
                    then(res)
                } else {
                    error(res.message.toString())
                }
            }

            override fun onFailure(call: Call, e: IOException) {
                Log.i("Error", e.message.toString())
                error("Error en el servicio")
            }
        })
    }

    fun getDetail(
        token: String,
        order: Long,
        then: (DetailResponse) -> Unit,
        error: (String) -> Unit
    ) {
        Log.i("token peticion detail",token)
        val url = "$serverUrl$routeBase$routeOrders/$order"
        get(url, token).enqueue(object : Callback {
            override fun onResponse(call: Call, response: Response) {

                val body = response.body?.string()

                Log.i("info detail sin parsear",body.toString())
                val gson = GsonBuilder().create()
                val res = gson.fromJson(body, DetailResponse::class.java)
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

    fun postTakeOrder(
        idOrder: Long,
        idUser: Long,
        dataTake: String,
        then: (TakeOrderResponse) -> Unit,
        error: (String) -> Unit
    ) {
        val url = serverUrl + routeBase + routePicker + routeTake
        val request = TakeOrderRequest(idOrder, idUser, dataTake)
        val json = Gson().toJson(request)
        post(url, json).enqueue(object : Callback {

            override fun onResponse(call: Call, response: Response) {
                val body = response.body?.string()

                val gson = GsonBuilder().create()
                val res = gson.fromJson(body, TakeOrderResponse::class.java)
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

    fun postReleaseOrder(
        idOrder: Long,
        idUser: Long,
        observations: String,
        then: (ReleaseOrderResponse) -> Unit,
        error: (String) -> Unit
    ) {
        val url = serverUrl + routeBase + routePicker + routeRelease
        val request = ReleaseOrderRequest(idOrder, idUser, observations)
        val json = Gson().toJson(request)
        post(url, json).enqueue(object : Callback {

            override fun onResponse(call: Call, response: Response) {
                val body = response.body?.string()

                val gson = GsonBuilder().create()
                val res = gson.fromJson(body, ReleaseOrderResponse::class.java)
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

    fun postPickingOrder(
        listDataPicker: List<ListDataPicker>,
        idOrder: Long,
        idUser: Long,
        productosok: Boolean,
        totalPicker: String,
        observations: String,
        then: (PickingOrderResponse) -> Unit,
        error: (String) -> Unit
    ) {
        val url = serverUrl + routeBase + routePicker + routePicking
        val request = PickingOrderRequest(listDataPicker,idOrder, idUser, productosok,totalPicker,observations)
        val json = Gson().toJson(request)
        post(url, json).enqueue(object : Callback {

            override fun onResponse(call: Call, response: Response) {
                val body = response.body?.string()

                val gson = GsonBuilder().create()
                val res = gson.fromJson(body, PickingOrderResponse::class.java)
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

    fun postDeliverCourier(
        idOrder: Long,
        idUser: Long,
        then: (DeliverCourierResponse) -> Unit,
        error: (String) -> Unit
    ) {
        val url = serverUrl + routeBase + routePicker + routeDeliverCourier
        val request = DeliverCourierRequest(idOrder, idUser)
        val json = Gson().toJson(request)
        post(url, json).enqueue(object : Callback {

            override fun onResponse(call: Call, response: Response) {
                val body = response.body?.string()

                val gson = GsonBuilder().create()
                val res = gson.fromJson(body, DeliverCourierResponse::class.java)
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

    fun postDeliverCustomer(
        idOrder: Long,
        idUser: Long,
        then: (DeliverConsumerResponse) -> Unit,
        error: (String) -> Unit
    ) {
        val url = serverUrl + routeBase + routePicker + routeDeliverConsumer
        val request = DeliverConsumerRequest(idOrder, idUser)
        val json = Gson().toJson(request)
        post(url, json).enqueue(object : Callback {

            override fun onResponse(call: Call, response: Response) {
                val body = response.body?.string()

                val gson = GsonBuilder().create()
                val res = gson.fromJson(body, DeliverConsumerResponse::class.java)
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