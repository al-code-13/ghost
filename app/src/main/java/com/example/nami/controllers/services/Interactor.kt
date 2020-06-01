package com.example.nami.controllers.services

import SectionResponse
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
            postLoginCoroutine(user, password, then, error)
        }
    }

    private suspend fun postLoginCoroutine(
        user: String,
        password: String,
        then: (LoginResponse) -> Unit,
        error: (String) -> Unit
    ) {
        val url = serverUrl + routeBase + routeAuth + routeLogin
        val request = LoginRequest(user, password)
        val json = Gson().toJson(request)
        withContext(Dispatchers.IO) {
            post(url, json).enqueue(object : Callback {

                override fun onResponse(call: Call, response: Response) {
                    val body = response.body?.string()

                    val gson = GsonBuilder().create()
                    val res = gson.fromJson(body, LoginResponse::class.java)
                    if (response.isSuccessful) {
                        token = res.token
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
        then: (SectionsResponse) -> Unit,
        error: (String) -> Unit
    ) {
        uiScope.launch {
            getSectionsCorrutine(then, error)
        }
    }

    fun getSectionsCorrutine(
        then: (SectionsResponse) -> Unit,
        error: (String) -> Unit
    ) {

        val url = serverUrl + routeBase + routeSections
        get(url, token).enqueue(object : Callback {
            override fun onResponse(call: Call, response: Response) {

                val body = response.body?.string()
                Log.i("bodySections",body.toString())
                val gson = GsonBuilder().create()
                val res = gson.fromJson(body, SectionsResponse::class.java)
                if (response.isSuccessful) {
                    data = res
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
        section: Int,
        then: (SectionResponse) -> Unit,
        error: (String) -> Unit
    ) {
        uiScope.launch {
            getSectionCorutine(section, then, error)
        }
    }

    private suspend fun getSectionCorutine(
        section: Int,
        then: (SectionResponse) -> Unit,
        error: (String) -> Unit
    ) {

        val url = "$serverUrl$routeBase$routeSections/$section"
        withContext(Dispatchers.IO) {
            get(url, token).enqueue(object : Callback {
                override fun onResponse(call: Call, response: Response) {

                    val body = response.body?.string()
                    Log.i("info sin parsear", body.toString())
                    val gson = GsonBuilder().create()
                    val res = gson.fromJson(body, SectionResponse::class.java)
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

    }

    fun getDetail(
        order: Int,
        then: (DetailResponse) -> Unit,
        error: (String) -> Unit
    ) {
        uiScope.launch {
            getDetailCorutine(order, then, error)
        }
    }

    private suspend fun getDetailCorutine(
        order: Int,
        then: (DetailResponse) -> Unit,
        error: (String) -> Unit
    ) {
        Log.i("token peticion detail", token)
        val url = "$serverUrl$routeBase$routeOrders/$order"
        withContext(Dispatchers.IO) {
            get(url, token).enqueue(object : Callback {
                override fun onResponse(call: Call, response: Response) {

                    val body = response.body?.string()

                    Log.i("info detail sin parsear", body.toString())
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

    }


    fun putTakeOrder(
        idOrder: Int,
        dataTake: String,
        then: (TakeOrderResponse) -> Unit,
        error: (String) -> Unit
    ) {
        uiScope.launch {
            putTakeOrdercorutine(idOrder, dataTake, then, error)
        }
    }

    private suspend fun putTakeOrdercorutine(
        idOrder: Int,
        dataTake: String,
        then: (TakeOrderResponse) -> Unit,
        error: (String) -> Unit
    ) {
        val url = serverUrl + routeBase + routeOrders + idOrder + routeTake
        val request = TakeOrderRequest(dataTake)
        val json = Gson().toJson(request)
        Log.i("jsonTake", json)
        withContext(Dispatchers.IO) {
            put( url,token, json).enqueue(object : Callback {

                override fun onResponse(call: Call, response: Response) {
                    val body = response.body?.string()
                    Log.i("takeservice", body.toString())
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

    }

    fun putReleaseOrder(
        idOrder: Int,
        observations: String,
        then: (ReleaseOrderResponse) -> Unit,
        error: (String) -> Unit
    ) {
        uiScope.launch {
            putReleaseOrderCorutine(idOrder, observations, then, error)
        }
    }

    private suspend fun putReleaseOrderCorutine(
        idOrder: Int,
        observations: String,
        then: (ReleaseOrderResponse) -> Unit,
        error: (String) -> Unit
    ) {
        val url = "$serverUrl$routeBase$routeOrders/$idOrder$routeRelease"
        val request = ReleaseOrderRequest(observations)
        val json = Gson().toJson(request)
        withContext(Dispatchers.IO) {
            put(url, token, json).enqueue(object : Callback {

                override fun onResponse(call: Call, response: Response) {
                    val body = response.body?.string()
                    Log.i("bodyRelease",body.toString())
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

    }

    fun postPickingOrder(
        listDataPicker: List<ListDataPicker>,
        idOrder: Int,
        idUser: Int,
        productosok: Boolean,
        totalPicker: String,
        observations: String,
        then: (PickingOrderResponse) -> Unit,
        error: (String) -> Unit
    ) {
        uiScope.launch {
            postPickingOrderCorutine(
                listDataPicker,
                idOrder,
                idUser,
                productosok,
                totalPicker,
                observations,
                then,
                error
            )
        }
    }

    private suspend fun postPickingOrderCorutine(
        listDataPicker: List<ListDataPicker>,
        idOrder: Int,
        idUser: Int,
        productosok: Boolean,
        totalPicker: String,
        observations: String,
        then: (PickingOrderResponse) -> Unit,
        error: (String) -> Unit
    ) {
        val url = "$serverUrl$routeBase$routeOrders/$idOrder$routePicking"
        val request = PickingOrderRequest(
            listDataPicker,
            idOrder,
            idUser,
            productosok,
            totalPicker,
            observations
        )
        val json = Gson().toJson(request)
        withContext(Dispatchers.IO) {
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

    }


    fun putDeliverCourier(
        idOrder: Int,
        then: (DeliverCourierResponse) -> Unit,
        error: (String) -> Unit
    ) {
        uiScope.launch {
            putDeliverCourierCorutine(idOrder, then, error)
        }
    }


    private suspend fun putDeliverCourierCorutine(
        idOrder: Int,
        then: (DeliverCourierResponse) -> Unit,
        error: (String) -> Unit
    ) {
        val url = "$serverUrl$routeBase$routeOrders/$idOrder$routeDeliverCourier"
        val request = DeliverCourierRequest(idOrder)
        val json = Gson().toJson(request)
        withContext(Dispatchers.IO) {
            put(token, url, json).enqueue(object : Callback {

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

    }

    fun putDeliverCustomer(
        idOrder: Int,
        then: (DeliverConsumerResponse) -> Unit,
        error: (String) -> Unit
    ) {
        uiScope.launch {
            putDeliverCustomerCorutine(idOrder, then, error)
        }
    }

    private suspend fun putDeliverCustomerCorutine(
        idOrder: Int,
        then: (DeliverConsumerResponse) -> Unit,
        error: (String) -> Unit
    ) {
        val url = "$serverUrl$routeBase$routeOrders/$idOrder$routeDeliverConsumer"
        val request = DeliverConsumerRequest(idOrder)
        val json = Gson().toJson(request)
        withContext(Dispatchers.IO) {
            put(token, url, json).enqueue(object : Callback {

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

    fun putFreeze(
        idOrder: Int,
        idReason: Int,
        then: (FreezeResponse) -> Unit,
        error: (String) -> Unit
    ) {
        uiScope.launch {
            putFreezeCorutine(idOrder, idReason, then, error)
        }
    }

    private suspend fun putFreezeCorutine(
        idOrder: Int,
        idReason: Int,
        then: (FreezeResponse) -> Unit,
        error: (String) -> Unit
    ) {
        val url = "$serverUrl$routeBase$routeOrders/$idOrder$routeFreeze"
        val request = FreezeRequest(idReason)
        val json = Gson().toJson(request)
        withContext(Dispatchers.IO) {
            put(token, url, json).enqueue(object : Callback {

                override fun onResponse(call: Call, response: Response) {
                    val body = response.body?.string()

                    val gson = GsonBuilder().create()
                    val res = gson.fromJson(body, FreezeResponse::class.java)
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
}