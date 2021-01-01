package com.project.travelme.Data

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*
import com.project.travelmedrivers.entities.Travel

class TravelDataSource : TravelDAO {
    private val database = FirebaseDatabase.getInstance()
    var isSuccessLiveData = MutableLiveData<Boolean>()
    lateinit var travelsList: MutableLiveData<MutableList<Travel>>
    var requestCount: Int = 0

    lateinit  var uid: String
    lateinit var key: String
    var notifyToTravel: TravelDAO.NotifyToTravelListListener? = null
    var travelRef: DatabaseReference
    val countRef = database.getReference("counter");

    constructor(l: TravelDAO.NotifyToTravelListListener) {
        notifyToTravel = l
        travelsList = MutableLiveData(mutableListOf<Travel>())
         uid = FirebaseAuth.getInstance().uid.toString()
        travelRef = database.getReference("Travels/$uid")
        travelRef.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                travelsList.value?.clear()
                if (dataSnapshot.exists()) {
                    for (travels in dataSnapshot.children) {

                        val travel = travels.getValue(Travel::class.java)
                        if (travel != null) {
                            travelsList.value?.add(travel)

                        }
                    }
                    Log.i("Change", "Data changed")
                    notifyToTravel?.onTravelsChanged()
                }
            }

            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }
        }
        )
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
        while (requestCount == 0);
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

    override fun getAllTravels(): MutableLiveData<MutableList<Travel>> {
        return travelsList
    }


    override fun isSuccess(): MutableLiveData<Boolean> {
        return isSuccessLiveData
    }

    override fun setNotifyToTravelListListener(l: TravelDAO.NotifyToTravelListListener) {
        notifyToTravel = l
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



