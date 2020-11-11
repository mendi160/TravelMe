package com.project.travelme.Entities

import com.google.firebase.database.Exclude
import com.project.travelme.Utils.Address
import com.project.travelme.Utils.Converters
import com.project.travelme.Utils.Enums.Status
import java.io.Serializable
import java.time.LocalDate


class Travel : Serializable {
    val name: String
    val phoneNumber: Int
    val email: String
    val sourceAdders: Address
    val destinationAddress: MutableList<Address>
    val passengers: Int
    val departureDate: LocalDate
    val returnDate: LocalDate
    val status: Status
    val serviceProvider: Map<String, Boolean>

    constructor(
        name: String,
        phoneNumber: Int,
        email: String,
        sourceAdders: Address,
        destinationAddress: MutableList<Address>,
        passengers: Int,
        departureDate: LocalDate,
        returnDate: LocalDate,
        status: Status,
        serviceProvider: Map<String, Boolean>
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
        result["departureDate"] = departureDate.toString()
        result["returnDate"] = returnDate.toString()
        result["status"] = Converters.fromStatusToString(status)
        return result
    }

}
