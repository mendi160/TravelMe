package com.project.travelme.Utils

class Address(private var _city: String, private var _street: String, private var _number: Int) {

    override fun toString(): String {
        return "Address: $_city, $_street, $_number"
    }
}

