package com.example.geofavs


import com.google.android.gms.maps.model.LatLng
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext


class MapPresenter( val db:LocationDB, val view: MapDelegate) {

    fun addGP(point: LatLng,name: String) {







    }



}

interface MapDelegate{
    fun drawMarker(gp: LatLng, info: String)

}
