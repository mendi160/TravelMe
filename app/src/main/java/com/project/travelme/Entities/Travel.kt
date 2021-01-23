package com.project.travelmedrivers.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.project.travelme.Utils.Enums.Status

@Entity(tableName = "travels")
class Travel {
    @PrimaryKey(autoGenerate = true)
    var id: String = ""
    var name: String = ""
    var phoneNumber: String = ""
    var email: String = ""
    var sourceAdders: String = ""
    var destinationAddress = mutableListOf<String>()
    var passengers: Int = 0
    var departureDate: String = ""
    var returnDate: String = ""
    var status = Status.SENT
    var serviceProvider = mutableMapOf("Select" to false)
}