package com.project.travelme.Data

import androidx.lifecycle.MutableLiveData
import androidx.room.*
import com.project.travelmedrivers.entities.Travel

@Dao
interface TravelDAO {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertTravel(travel: Travel)

    //    @Delete
//    fun deleteTravel(num: Int)
//
   @Update
    fun updateTravel(travel: Travel)
//
//    @Query("SELECT * FROM travel_table WHERE :num == id")
//    fun getTravel(num: Int): LiveData<Travel>
//
    // @Query("DELETE FROM travel_table")
//    fun deleteAllTravels()
//
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