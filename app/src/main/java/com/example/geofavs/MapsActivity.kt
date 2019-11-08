package com.example.geofavs


import android.location.Geocoder
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log

import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.Marker
import com.google.android.gms.maps.model.MarkerOptions
import java.io.IOException
import java.util.*

class MapsActivity : AppCompatActivity(), OnMapReadyCallback,
    GoogleMap.OnInfoWindowLongClickListener, MapDelegate {
    private lateinit var mMap: GoogleMap
    private lateinit var presenter: MapPresenter

    override fun onInfoWindowLongClick(p0: Marker) {
        val point = p0.position
        val name = p0.title
        presenter.addGP(p0.position,getAddress(p0.position))


    }


    override fun drawMarker(gp: LatLng, info: String) {
        val markerOptions = MarkerOptions().position(gp)
        Log.e("pasa2",info)
        markerOptions.title(info)




        mMap.addMarker(markerOptions).showInfoWindow()
    }




    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_maps)
        val db = LocationFactory.get(this)
        presenter = MapPresenter(db, this)

        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        val mapFragment = supportFragmentManager
            .findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)

    }

    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    override fun onMapReady(googleMap: GoogleMap) {
        mMap = googleMap
        mMap.setOnMapClickListener {
            var marker = MarkerOptions().position(it)
            marker.title(getAddress(it))
            mMap.addMarker(marker)




        }
        mMap.setOnInfoWindowLongClickListener(this)


    }

    private fun getAddress(latLng: LatLng): String {
        // 1
        val geocoder = Geocoder(this, Locale.getDefault())



        val addresses = geocoder.getFromLocation(
            latLng.latitude,
            latLng.longitude,
            1
        ) // Here 1 represent max location result to returned, by documents it recommended 1 to 5

        val address = addresses.get(0).getAddressLine(0)
        return address
    }

}
