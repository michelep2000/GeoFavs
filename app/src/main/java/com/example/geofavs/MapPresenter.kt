package com.example.geofavs

import android.location.Address
import android.location.Geocoder
import com.google.android.gms.maps.model.LatLng
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.io.IOException

class MapPresenter( val db:LocationDB, val view: MapDelegate, val geocode: Geocoder) {

    fun addGP(point: LatLng) {
        CoroutineScope(Dispatchers.IO).launch {
            db.locationDao().insertLocation(Location(longitude = point.longitude,latitude = point.latitude))
            val address = getAddress(point)
            withContext(Dispatchers.Main){
                view.drawMarker(point,address)
            }

        }





    }

    private fun getAddress(latLng: LatLng): String {
        // 1

        val addresses: List<Address>?
        val address: Address?
        var addressText = ""

        try {
            // 2
            addresses = geocode.getFromLocation(latLng.latitude, latLng.longitude, 1)
            // 3
            if (null != addresses && !addresses.isEmpty()) {
                address = addresses[0]
                for (i in 0 until address.maxAddressLineIndex) {
                    addressText += if (i == 0) address.getAddressLine(i) else "\n" + address.getAddressLine(i)
                }
            }
        } catch (e: IOException) {

        }

        return addressText
    }

}

interface MapDelegate{
    fun drawMarker(gp: LatLng, info: String)

}
