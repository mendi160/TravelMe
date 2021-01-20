package com.project.travelme.Data

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*
import com.project.travelmedrivers.entities.Travel

class TravelDataSource private constructor() : TravelDAO {
    private object HOLDER {
        val INSTANCE = TravelDataSource()
    }

    companion object {
        val instance: TravelDataSource by lazy { HOLDER.INSTANCE }
    }

    private val database = FirebaseDatabase.getInstance()
    private val referenceMap = mutableMapOf<String, DatabaseReference>()
    var isSuccessLiveData = MutableLiveData<Boolean>()
    var travelsList = MutableLiveData(mutableListOf<Travel>())
    var requestCount: Int = 0
    private var uid: String = FirebaseAuth.getInstance().uid.toString()
    lateinit var key: String
    var notifyToTravel: TravelDAO.NotifyToTravelListListener? = null
    private var travelRef: DatabaseReference
    private val countRef = database.getReference("counter");

    init {
        travelRef = database.getReference("Travels/${FirebaseAuth.getInstance().uid.toString()}")
        travelRef.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                travelsList.value?.clear()
                if (dataSnapshot.exists()) {
                    for (travels in dataSnapshot.children) {
                        val travel = travels.getValue(Travel::class.java)
                        if (travel != null) {
                            referenceMap[travel.id] = travels.ref
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
        val ref3 = ref2?.child("Request_$requestCount")
        if (ref3 != null) {
            key = ref3.push().key.toString()
            travel.id = key;
        };
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

    override fun updateTravel(travel: Travel) {
        referenceMap[travel.id]!!.setValue(travel)
            .addOnSuccessListener { isSuccessLiveData.postValue(true) }
            .addOnFailureListener { isSuccessLiveData.postValue(false) }
    }

    override fun isSuccess(): MutableLiveData<Boolean> {
        return isSuccessLiveData
    }

    override fun setNotifyToTravelListListener(l: TravelDAO.NotifyToTravelListListener) {
        notifyToTravel = l
    }

    override fun cancelTravel(travel: Travel) {
        referenceMap[travel.id]!!.removeValue()
        database.getReference("canceledTravels/${travel.id}").setValue(travel)
    }

    fun getTravelOfUser(string: String) {
        val ref = database.getReference("Travels")
        val user = FirebaseAuth.getInstance().currentUser
        user?.let { ref.child(it.uid) }?.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                requestCount = snapshot.childrenCount.toInt()
            }

            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }
        })


    }
}
//
//    override fun deleteTravel(num: Int) {
//        TODO("Not yet implemented")
//    }
//

//
//    override fun getTravel(num: Int): LiveData<Travel> {
//        TODO("Not yet implemented")
//    }
//
//    override fun deleteAllTravels() {
//        TODO("Not yet implemented")
//    }



