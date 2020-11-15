package com.project.travelme.Ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.project.travelme.Data.TravelRepository
import com.project.travelme.Entities.Travel

class TravelViewModel : ViewModel() {
   lateinit var  ld:LiveData<Boolean>
    val repo =TravelRepository()
    fun saveTravel(travel : Travel)
    {
        repo.add(travel )

    }
    fun getIsSuccess(): MutableLiveData<Boolean> {
        return repo.dao.isSuccess()
    }




}