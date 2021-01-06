package com.project.travelme.Ui

import android.annotation.SuppressLint
import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.project.travelme.Data.TravelRepository
import com.project.travelme.Utils.Enums.Status
import com.project.travelmedrivers.entities.Travel

class TravelViewModel() : ViewModel() {

    lateinit var ld: LiveData<Boolean>
    var travelList = MutableLiveData<List<Travel>>()
    val repo: TravelRepository

    init {
        repo = TravelRepository.instance
        repo.getAllTravels()
            .observeForever { travelList.postValue(repo.getAllTravels().value?.filter { it -> it.status != Status.CLOSED }) }

    }

    @SuppressLint("RestrictedApi")


    fun insertTravel(context: Context, travel: Travel) {
        repo.insert(travel)
    }

    fun getIsSuccess(): MutableLiveData<Boolean> {
        return TravelRepository.dao.isSuccess()
    }

    fun getAllTravels(): MutableLiveData<List<Travel>> {
        return travelList
    }
    fun updateTravel(travel: Travel){
        repo.updateTravel(travel)
    }
}