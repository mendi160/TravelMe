package com.project.travelme.Data
import  com.project.travelme.Data.TravelRoomDataBase
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.room.*
import com.project.travelme.Entities.Travel

@Dao
interface TravelDAO {
    @Insert
    fun insertTravel(travel: Travel)

    @Delete
    fun deleteTravel(num: Int)

    @Update
    fun updateTravel(travel: Travel)

    @Query("SELECT * FROM travel_table WHERE :num == id")
    fun getTravel(num: Int): LiveData<Travel>
    fun isSuccess(): MutableLiveData<Boolean>

}