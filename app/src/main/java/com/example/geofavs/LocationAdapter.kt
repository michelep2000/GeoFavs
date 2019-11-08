package com.example.geofavs

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import model.Location

class LocationAdapter (private val listener: (Location) -> Unit): RecyclerView.Adapter<LocationAdapter.ViewHolder>(){

    private var locations = listOf<Location>()

    fun addLocation(newLocation: List<Location>) {
        this.locations = newLocation
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder.from(parent)
    }

    override fun getItemCount(): Int = locations.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(locations[position])
    }

    class ViewHolder private constructor(view: View) : RecyclerView.ViewHolder(view) {
        private val locationTxt = view.findViewById<TextView>(R.id.txtLocationFav)


        fun bind(location: Location) {
            locationTxt.text = location.lon.toString()


        }

        companion object {
            fun from(parent: ViewGroup): ViewHolder {
                val view =
                    LayoutInflater.from(parent.context).inflate(R.layout.location_item, parent, false)
                return ViewHolder(view)
            }
        }
    }
}