package com.project.travelme.Ui

import android.app.Service
import android.content.Intent
import android.os.IBinder
import com.project.travelme.Data.TravelDataSource

class MyService : Service() {
    override fun onCreate() {
        super.onCreate()
        TravelDataSource.instance.isSuccessLiveData.observeForever {
            sendBroadcast(
                Intent().setAction("TravelMe.TravelAdded")
            )
        }
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        return START_STICKY
    }

    override fun onBind(intent: Intent): IBinder? {
        return null
    }
}