package com.project.travelme.Data

import androidx.lifecycle.MutableLiveData
import com.project.travelme.Entities.Travel

class TravelRepository {

    lateinit var dao: TravelDAO

constructor(){
    dao=TravelDataSource()

}
    fun add (travel : Travel)
    {
        dao.addTravel(travel)
    }
    fun remove (){}
    fun  update (){}
    fun get (){}

}