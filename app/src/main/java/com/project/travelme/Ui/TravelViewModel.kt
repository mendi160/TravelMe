package com.project.travelme.Ui

import android.app.Application
import android.content.Context
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.project.travelme.Data.TravelRepository
import com.project.travelme.Entities.Travel


class TravelViewModel : ViewModel() {
    lateinit var ld: LiveData<Boolean>
    var liveDataLogin: LiveData<Travel>? = null

    fun saveTravel(context: Context,travel: Travel) {
        TravelRepository.add(context,travel)

    }

//    fun getIsSuccess(): MutableLiveData<Boolean> {
//        return repo.dao.isSuccess()
//    }


//    fun insertItem(item: Travel) {
//        repo?.add(item)
//    }

//    fun getItems(): LiveData<Item?>? {
//        return rp.getItems()
//    }

}
//class LoginViewModel : ViewModel() {
//
//    fun getLoginDetails(context: Context, username: String) : LiveData<LoginTableModel>? {
//        liveDataLogin = LoginRepository.getLoginDetails(context, username)
//        return liveDataLogin
//    }
//
//}