package com.example.benjohnson.openshadowapp

import android.os.Handler
import android.os.Looper
import com.example.benjohnson.openshadowapp.NetworkingUtils.Companion.BASE_URL
import com.google.gson.GsonBuilder
import okhttp3.*
import java.io.IOException

/**
 * Created by BenJohnson on 18/02/2018.
 */
class MainActivityData {


    /**
     * Default constructor
     */
    private val client = OkHttpClient()

    /**
     * Makes a request to obtain the closest city
     */
    fun searchForClosestCity(dataReadyCallback: IDataReadyCallBack.IClosestCityDataReady, latitude: Double, longitude: Double) {
        //Create request
        val request = Request.Builder().url(buildUrl(latitude.toString(), longitude.toString())).build()
        //Schedule call to be executed in async task
        client.newCall(request).enqueue(object : Callback {
            override fun onFailure(call: Call, e: IOException) {
                e.printStackTrace()
            }

            override fun onResponse(call: Call, response: Response) {
                val gson = GsonBuilder().create()
                val mainHandler = Handler(Looper.getMainLooper())
                val myRunnable = Runnable {
                    dataReadyCallback.onDataReady(gson.fromJson(response.toString(), ClosestCityResponse::class.java).toString())
                }
                mainHandler.post(myRunnable)
            }
        })


    }

    /**
     * Builds the url with endpoint and required parameters
     */
    private fun buildUrl(lat: String, long: String): String {
        val urlBuilder = HttpUrl.parse(BASE_URL)!!.newBuilder()
        return urlBuilder.build().toString()
    }


}
