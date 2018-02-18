package com.example.benjohnson.openshadowapp



/**
 * @class IDataReadyCallback
 *
 * A set of data ready callbacks
 */
interface IDataReadyCallBack {

    /**
     * Callback for when nearest city is available
     */
    interface IClosestCityDataReady {
        fun onDataReady(data: String)
    }

}