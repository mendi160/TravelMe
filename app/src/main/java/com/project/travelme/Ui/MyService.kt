package com.project.travelme.Ui

import android.app.Service
import android.content.Intent
import android.os.IBinder
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.project.travelme.Data.TravelDataSource

class MyService : Service() {
    var isTravelAdded: Boolean = false
    override fun onCreate() {
        super.onCreate()
        TravelDataSource.instance.isSuccessLiveData.observeForever { updateIsTravelAdded(true) }
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        Thread() {
            while (true) {
                if (isTravelAdded) {
                    updateIsTravelAdded(false)
                    sendBroadcast(Intent().setAction("TravelMe.TravelAdded"))
                }
                Thread.sleep(5000)
            }
        }.start()
        return START_STICKY
    }

    override fun onBind(intent: Intent): IBinder? {
        return null
    }

    @Synchronized
    fun updateIsTravelAdded(state: Boolean) {
        isTravelAdded = state
    }
}