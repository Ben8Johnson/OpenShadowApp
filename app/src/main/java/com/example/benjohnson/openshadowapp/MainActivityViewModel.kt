package com.example.benjohnson.openshadowapp

import android.arch.lifecycle.ViewModel
import android.location.Location

/**
 * Created by BenJohnson on 18/02/2018.
 */
class MainActivityViewModel : ViewModel(),IDataReadyCallBack.IClosestCityDataReady {

    var showLocationButton: Boolean = true
    val dataClass = MainActivityData()

    /**
     * Called when the users location is avaiable
     */
    fun locationAvailable(location: Location) {
        showLocationButton = false
        //Make request for the closest city
        dataClass.searchForClosestCity(this,location.latitude,location.longitude)
    }

    override fun onDataReady(data: String) {

    }
}