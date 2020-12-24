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
    var requestCount: Int = 0
    lateinit var key: String
    val countRef = database.getReference("counter");

    constructor() {
        countRef.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                requestCount = snapshot.child("val").value.toString().toInt()

            }
            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }
        })
    }

    override fun insertTravel(travel: Travel) {
        while (requestCount==0);
        val user = FirebaseAuth.getInstance().currentUser
        val ref = database.getReference("Travels")
        val ref2 = user?.let { ref.child(it.uid) }
        ref2?.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                //   requestCount = snapshot.children.to

            }

            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }
        })
        val ref3 = ref2?.child("Request_$requestCount")
        if (ref3 != null) {
            key = ref3.push().key.toString()
        };
        travel.id = key;
        ref3?.setValue(travel)?.addOnSuccessListener {
            isSuccessLiveData.postValue(true)
            countRef.child("val").setValue(requestCount + 1)

        }?.addOnFailureListener {
            isSuccessLiveData.postValue(false)
        }
    }


    override fun isSuccess(): MutableLiveData<Boolean> {
        return isSuccessLiveData
    }

    fun getTravelOfUser(string: String) {
        val ref = database.getReference("Travels")
        val user = FirebaseAuth.getInstance().currentUser
        val ref2 = user?.let { ref.child(it.uid) }
        if (ref2 != null) {
            ref2.addValueEventListener(object : ValueEventListener {
                override fun onDataChange(snapshot: DataSnapshot) {
                    requestCount = snapshot.childrenCount.toInt()
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



