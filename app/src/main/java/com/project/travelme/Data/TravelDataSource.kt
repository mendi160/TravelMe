package com.project.travelme.Data

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase
import com.project.travelme.Entities.Travel

class TravelDataSource : TravelDAO {
    private  val database = FirebaseDatabase.getInstance()
    var isSuccessLiveData = MutableLiveData<Boolean>()
    override fun insertTravel(travel: Travel) {
        val user = FirebaseAuth.getInstance().currentUser
        val ref = database.getReference("Travels")
        val ref2 = user?.let { ref.child(it.uid) }

        if (ref2 != null) {
            ref2.setValue(travel).addOnSuccessListener {
                isSuccessLiveData.postValue(true)

            }.addOnFailureListener { isSuccessLiveData.postValue(false) }
        }
    }

    override fun isSuccess(): MutableLiveData<Boolean> {
     return isSuccessLiveData
    }
//
//    override fun deleteTravel(num: Int) {
//        TODO("Not yet implemented")
//    }
//
//    override fun updateTravel(travel: Travel) {
//        TODO("Not yet implemented")
//    }
//
//    override fun getTravel(num: Int): LiveData<Travel> {
//        TODO("Not yet implemented")
//    }
//
//    override fun deleteAllTravels() {
//        TODO("Not yet implemented")
//    }
//
//    override fun getAllTravels(): LiveData<List<Travel>> {
//        TODO("Not yet implemented")
//    }

//    override fun isSuccess(): MutableLiveData<Boolean> {
//        return isSuccessLiveData
//    }


}