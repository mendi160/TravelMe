package com.project.travelme.Utils

import com.google.firebase.database.Exclude
import java.io.Serializable

class Address : Serializable {
    private val _city: String
    private val _street: String
    private val _number: Int

    constructor(_city: String, _street: String, _number: Int) {
        this._city = _city
        this._street = _street
        this._number = _number
    }

    override fun toString(): String {
        return "Address: $_city, $_street, $_number"
    }

    @Exclude
    fun toMap(): Map<String, Any>? {
        val result: HashMap<String, Any> = HashMap()
        result["_city"] = _city
        result["_street"] = _street
        result["_number"] = _number
        return result
    }
}

