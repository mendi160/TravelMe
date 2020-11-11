package com.project.travelme.Entities

import com.google.firebase.database.Exclude
import com.project.travelme.Utils.Address
import com.project.travelme.Utils.Converters
import com.project.travelme.Utils.Enums.Status
import java.io.Serializable
import java.util.*
import kotlin.collections.HashMap


class Travel : Serializable {
    val name: String
    val phoneNumber: Int
    val email: String
    val sourceAdders: Address
    val destinationAddress: MutableList<Address>
    val passengers: Int
    val departureDate: GregorianCalendar
    val returnDate: GregorianCalendar
    var status: Status
    var serviceProvider: Map<String, Boolean>

    constructor(
        name: String,
        phoneNumber: Int,
        email: String,
        sourceAdders: Address,
        destinationAddress: MutableList<Address>,
        passengers: Int,
        departureDate: GregorianCalendar,
        returnDate: GregorianCalendar,
        status: Status,
        serviceProvider: Map<String, Boolean> = mapOf(" " to false)
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

    @Exclude
    fun toMap(): Map<String, Any>? {
        val result: HashMap<String, Any> = HashMap()
        result["name"] = name
        result["phoneNumber"] = phoneNumber
        result["email"] = email
        result["passengers"] = passengers
        result["departureDate"] = Converters.fromGeorgianCalenderToString(departureDate)
        result["returnDate"] = Converters.fromGeorgianCalenderToString(returnDate)
        //  result["SourceAdders"] = sourceAdders.toMap().toString()
        result["status"] = Converters.fromStatusToString(status)
        return result
    }

}
