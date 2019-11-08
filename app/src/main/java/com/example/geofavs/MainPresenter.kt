package com.example.geofavs

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MainPresenter(val db:LocationDB,val delegate: MainDelegate) {
    fun getLocations() {
        CoroutineScope(Dispatchers.IO).launch {
            val list = db.locationDao().getAll()
            withContext(Dispatchers.Main){
                 delegate.renewList(list)
            }
        }

    }


}
interface MainDelegate{
    fun renewList(list :List<Location>)
}