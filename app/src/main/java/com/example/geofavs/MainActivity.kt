package com.example.geofavs

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_main.*
import model.Location

class MainActivity : AppCompatActivity() {

    lateinit var locationsAdapter: LocationAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

//        val presenter = LocationPresenter(this)

        locationsRecyclerView.layoutManager = LinearLayoutManager(this)
        locationsRecyclerView.setHasFixedSize(true)
        locationsAdapter = LocationAdapter {

        }
        locationsRecyclerView.adapter = locationsAdapter


    }
}


