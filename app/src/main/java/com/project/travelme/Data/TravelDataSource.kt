package com.project.travelme.Data

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.google.firebase.database.FirebaseDatabase
import com.project.travelme.Entities.Travel

class TravelDataSource : TravelDAO {
    val database = FirebaseDatabase.getInstance()
    var isSuccessLiveData = MutableLiveData<Boolean>()


    override fun insertTravel(travel: Travel) {
        var ins = FirebaseDatabase.getInstance()
        var ref = ins.getReference("Travels")
        var ref2 = ref.child(travel.phoneNumber.toString())

        ref2.setValue(travel).addOnSuccessListener {
            isSuccessLiveData.postValue(true)

        }.addOnFailureListener { isSuccessLiveData.postValue(false) }
    }

    override fun deleteTravel(num: Int) {
        TODO("Not yet implemented")
    }

    override fun updateTravel(travel: Travel) {
        TODO("Not yet implemented")
    }

    override fun getTravel(num: Int): LiveData<Travel> {
        TODO("Not yet implemented")
    }

    override fun isSuccess(): MutableLiveData<Boolean> {
        return isSuccessLiveData
    }


}