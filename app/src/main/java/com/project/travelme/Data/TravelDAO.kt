package com.project.travelme.Data

import androidx.lifecycle.MutableLiveData
import androidx.room.*
import com.project.travelmedrivers.entities.Travel

@Dao
interface TravelDAO {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertTravel(travel: Travel)
   @Update
    fun updateTravel(travel: Travel)
    @Query("SELECT * FROM travels")
    fun getAllTravels(): MutableLiveData<MutableList<Travel>>

    fun isSuccess(): MutableLiveData<Boolean>
    fun setNotifyToTravelListListener(notifyToTravelListListener: NotifyToTravelListListener)

    interface NotifyToTravelListListener {
        fun onTravelsChanged()
    }
    @Update
    fun cancelTravel(travel: Travel)

}