package com.project.travelme.Entities

import com.google.firebase.database.Exclude
import com.project.travelme.Utils.Address
import com.project.travelme.Utils.Converters
import com.project.travelme.Utils.Enums.Status
import java.util.*
import kotlin.collections.HashMap
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "travel_table")
class Travel {
    @PrimaryKey(autoGenerate = true)
    private
    var id : Int = 1000
    var name: String
        private set
        get() = field
    var phoneNumber: Int
        private set
        get() = field
    var email: String
        private set
        get() = field
    var sourceAdders: Address
        private set
        get() = field
    var destinationAddress: MutableList<Address>
        private set
        get() = field
    var passengers: Int
        private set
        get() = field
    var departureDate: String
        private set
        get() = field
    var returnDate: String
        private set
        get() = field
    var status: Status
        private set
        get() = field
    var serviceProvider: Map<String, Boolean>
        private set
        get() = field

    constructor(
        name: String,
        phoneNumber: Int,
        email: String,
        sourceAdders: Address,
        destinationAddress: MutableList<Address>,
        passengers: Int,
        departureDate: String,
        returnDate: String,
        status: Status,
        serviceProvider: Map<String, Boolean> = mapOf(" " to false),
    ) {
        this.name = name
        this.phoneNumber = phoneNumber
        this.email = email
        this.sourceAdders = sourceAdders
        this.destinationAddress = destinationAddress
        this.passengers = passengers
        this.departureDate = departureDate
        this.returnDate = returnDate
        this.status = status
        this.serviceProvider = serviceProvider
    }
//
//    @Exclude
//    fun toMap(): Map<String, Any>? {
//        val result: HashMap<String, Any> = HashMap()
//        result["name"] = name
//        result["phoneNumber"] = phoneNumber
//        result["email"] = email
//        result["passengers"] = passengers
//        result["departureDate"] = Converters.fromGeorgianCalenderToString(departureDate)
//        result["returnDate"] = Converters.fromGeorgianCalenderToString(returnDate)
//        //  result["SourceAdders"] = sourceAdders.toMap().toString()
//        result["status"] = Converters.fromStatusToString(status)
//        return result
//    }

}
