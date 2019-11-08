package com.example.geofavs

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext


class MainActivity : AppCompatActivity(), MainDelegate {

    override fun renewList(list: List<Location>) {
        print("-----------------$list-------------------")
        locationsAdapter.addLocation(list)
    }

    lateinit var db: LocationDB
    lateinit var locationsAdapter: LocationAdapter

    private lateinit var presenter: MainPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        db = LocationFactory.get(this)
//        val presenter = LocationPresenter(this)

        locationsRecyclerView.layoutManager = LinearLayoutManager(this)
        locationsRecyclerView.setHasFixedSize(true)
        locationsAdapter = LocationAdapter()
        locationsRecyclerView.adapter = locationsAdapter
        btnMaps.setOnClickListener {
            val intent = Intent(this, MapsActivity::class.java)
            startActivity(intent)
        }
        presenter = MainPresenter(db, this)
        presenter.getLocations()


    }

    override fun onRestart() {
        super.onRestart()


        presenter.getLocations()

    }

    override fun onResume() {
        super.onResume()
        presenter.getLocations()
    }
}


