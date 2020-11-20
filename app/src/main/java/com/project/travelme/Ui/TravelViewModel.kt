package com.project.travelme.Ui

import android.app.Application
import android.content.Context
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.project.travelme.Data.TravelRepository
import com.project.travelme.Entities.Travel


class TravelViewModel : AndroidViewModel {
   lateinit var  ld:LiveData<Boolean>
    val repo :TravelRepository
    fun saveTravel(travel: Travel)
    {
        repo.add(travel)

    }
    fun getIsSuccess(): MutableLiveData<Boolean> {
        return repo.dao.isSuccess()
    }
    var r: Context = getApplication()

    constructor(application: Application):super(application) {
        repo = TravelRepository(r)
    }

    fun insertItem(item: Travel) {
        repo?.add(item)
    }

//    fun getItems(): LiveData<Item?>? {
//        return rp.getItems()
//    }

}