package com.project.travelme.Ui

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.project.travelme.Data.TravelRepository
import com.project.travelme.Entities.Travel


class TravelViewModel : ViewModel() {
    lateinit var ld: LiveData<Boolean>
    var liveDataLogin: LiveData<Travel>? = null

    fun insertTravel(context: Context, travel: Travel) {
        TravelRepository.insert(context, travel)
    }

    fun getIsSuccess(): MutableLiveData<Boolean> {
        return TravelRepository.dao.isSuccess()
    }
}