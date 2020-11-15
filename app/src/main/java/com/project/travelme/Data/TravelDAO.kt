package com.project.travelme.Data

import androidx.lifecycle.MutableLiveData
import com.project.travelme.Entities.Travel

interface TravelDAO {
    fun addTravel(travel : Travel)
    fun removeTravel(num : Int)
    fun updateTravel(travel : Travel)
    fun getTravel(num : Int):Travel
    fun isSuccess():MutableLiveData<Boolean>

}