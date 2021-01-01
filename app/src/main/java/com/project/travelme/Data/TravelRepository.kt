package com.project.travelme.Data

import android.annotation.SuppressLint
import android.content.Context
import androidx.lifecycle.MutableLiveData
import com.project.travelmedrivers.entities.Travel


@SuppressLint("RestrictedApi")
class TravelRepository(val context: Context) {

    init {
        database = initializeDB(context)
        val notifyToTravelListListener: TravelDAO.NotifyToTravelListListener =
            object : TravelDAO.NotifyToTravelListListener {
                override fun onTravelsChanged() {
                    //userTravels = database!!.getAllTravels()
                    userTravels.postValue(database!!.getAllTravels().value)

                }
            }
        database!!.setNotifyToTravelListListener(notifyToTravelListListener)


    }

    companion object {

        var userTravels = MutableLiveData<MutableList<Travel>>()
        var travelDatabase: TravelDataSource? = null
        var database: TravelDAO? = null
        var dao: TravelDAO = TravelDataSource(object : TravelDAO.NotifyToTravelListListener {
            override fun onTravelsChanged() {
                //userTravels = database!!.getAllTravels()
                this@Companion.userTravels.postValue(database!!.getAllTravels().value)

            }
        })


        var itemDAO: TravelDAO? = null


        private fun initializeDB(context: Context): TravelDAO {

            return TravelDataSource(object : TravelDAO.NotifyToTravelListListener {
                override fun onTravelsChanged() {
                    //userTravels = database!!.getAllTravels()
                    this@Companion.userTravels.postValue(database!!.getAllTravels().value)

                }
            })


            // return TravelRoomDataBase.getDatabase(context) as TravelDAO
        }


        fun insert(item: Travel) {


            Thread() {
                dao.insertTravel(item)
            }.start();

        }


    }

    fun getAllTravels(): MutableLiveData<MutableList<Travel>> {

        return userTravels
    }
}
