package com.project.travelme.Data

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.room.*
import com.project.travelme.Entities.Travel

@Dao
interface TravelDAO {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertTravel(travel: Travel)

//    @Delete
//    fun deleteTravel(num: Int)
//
//    @Update
//    fun updateTravel(travel: Travel)
//
//    @Query("SELECT * FROM travel_table WHERE :num == id")
//    fun getTravel(num: Int): LiveData<Travel>
//
//    @Query("DELETE FROM travel_table")
//    fun deleteAllTravels()
//
//    @Query("SELECT * FROM travel_table")
//    fun getAllTravels(): LiveData<List<Travel>>

    fun isSuccess(): MutableLiveData<Boolean>

}