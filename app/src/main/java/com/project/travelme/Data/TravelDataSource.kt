package com.project.travelme.Data

import androidx.lifecycle.MutableLiveData
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.project.travelme.Entities.Travel

class TravelDataSource : TravelDAO {
    private val database = FirebaseDatabase.getInstance()
    var isSuccessLiveData = MutableLiveData<Boolean>()
    var cildCount :Int =0
    val user = FirebaseAuth.getInstance().currentUser
    val ref = database.getReference("Travels")
    val ref2 = user?.let { ref.child(it.uid) }

    constructor(){
        ref2?.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                cildCount= snapshot.childrenCount.toInt()
            }

            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }
        })
    }
    override fun insertTravel(travel: Travel) {
        val ref3= ref2?.child("Request_$cildCount")
        ref3?.setValue(travel)?.addOnSuccessListener {
            isSuccessLiveData.postValue(true)

        }?.addOnFailureListener {
            isSuccessLiveData.postValue(false) }
    }


    override fun isSuccess(): MutableLiveData<Boolean> {
        return isSuccessLiveData
    }

    fun getTravelofUser(string: String) {
        val ref = database.getReference("Travels")
        val user = FirebaseAuth.getInstance().currentUser
        val ref2 = user?.let { ref.child(it.uid) }
        if (ref2 != null) {
            ref2.addValueEventListener(object : ValueEventListener {
                override fun onDataChange(snapshot: DataSnapshot) {
                    cildCount= snapshot.childrenCount.toInt()
                }

                override fun onCancelled(error: DatabaseError) {
                    TODO("Not yet implemented")
                }
            })

        }


    }
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



