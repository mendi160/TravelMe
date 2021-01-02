package com.project.travelme.Data

import android.annotation.SuppressLint
import androidx.lifecycle.MutableLiveData
import com.project.travelmedrivers.entities.Travel

@SuppressLint("RestrictedApi")
class TravelRepository private constructor() {
    val database = TravelDataSource.instance
    var userTravels: MutableLiveData<MutableList<Travel>>

    private object HOLDER {
        val INSTANCE = TravelRepository()
    }

    companion object {
        val instance: TravelRepository by lazy { HOLDER.INSTANCE }
        var dao: TravelDAO = TravelDataSource.instance
    }

    init {
        userTravels = dao.getAllTravels()
        val notifyToTravelListListener: TravelDAO.NotifyToTravelListListener =
            object : TravelDAO.NotifyToTravelListListener {
                override fun onTravelsChanged() {
                    userTravels.postValue(database!!.getAllTravels().value)
                }
            }
        database.setNotifyToTravelListListener(notifyToTravelListListener)
    }

    fun getAllTravels(): MutableLiveData<MutableList<Travel>> {
        return userTravels
    }

    fun insert(item: Travel) {
        Thread() {
            dao.insertTravel(item)
        }.start();
    }
}
