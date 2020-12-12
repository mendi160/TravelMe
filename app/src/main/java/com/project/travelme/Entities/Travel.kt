package com.project.travelme.Entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.android.libraries.places.api.model.Place
import com.google.gson.annotations.SerializedName
import com.project.travelme.Utils.Address
import com.project.travelme.Utils.Enums.Status

@Entity(tableName = "travel_table")
class Travel {
    @PrimaryKey(autoGenerate = true)
    var id: Int = 1000
        private set
        get() = field
    var name: String
        private set
        get() = field
    var phoneNumber: Int
        private set
        get() = field
    var email: String
        private set
        get() = field
    var sourceAdders: String
        private set
        get() = field

    //@TypeConverters(addCon::class)
    var destinationAddress: MutableList<String>
        private set
        get() = field

    var passengers: Int
        private set
        get() = field

    var departureDate: String
    private set
        get() = field

    var returnDate: String
        set
        get() = field

    var status: Status
        private set
        get() = field
    var serviceProvider: Map<String, Boolean>
        private set
        get() = field

    constructor(
        id: Int,
        name: String,
        phoneNumber: Int,
        email: String,
        sourceAdders: String,
        destinationAddress: MutableList<String>,
        passengers: Int,
        departureDate: String,
        returnDate: String,
        status: Status,
        serviceProvider: Map<String, Boolean> = mapOf(" " to false),
    ) {
        this.id = id
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
