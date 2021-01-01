package com.project.travelme.Ui

import android.annotation.SuppressLint
import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.firebase.ui.auth.AuthUI.getApplicationContext
import com.project.travelme.Data.TravelRepository
import com.project.travelme.Utils.Enums.Status
import com.project.travelmedrivers.entities.Travel


class TravelViewModel : ViewModel() {
    lateinit var ld: LiveData<Boolean>
        var  travelList=MutableLiveData<List<Travel>>()

    @SuppressLint("RestrictedApi")
    val repo = TravelRepository(getApplicationContext())


    fun insertTravel(context: Context, travel: Travel) {
        TravelRepository.insert(travel)
    }

    fun getIsSuccess(): MutableLiveData<Boolean> {
        return TravelRepository.dao.isSuccess()
    }

    fun getAllTravels(): MutableLiveData<List<Travel>> {
        travelList.value = repo.getAllTravels().value?.filter { it -> it.status != Status.CLOSED }
        return travelList
    }
}