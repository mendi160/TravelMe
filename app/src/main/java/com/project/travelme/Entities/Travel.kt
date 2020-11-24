package com.project.travelme.Entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.project.travelme.Utils.Address
import com.project.travelme.Utils.Converters
import com.project.travelme.Utils.addCon

@Entity(tableName = "travel_table")
class Travel {
    @ColumnInfo(name = "id")
    @PrimaryKey(autoGenerate = true)
    var id: Int=1000
        private set
        get() = field

    @ColumnInfo(name = "name")
    var name: String
        private set
        get() = field

    @ColumnInfo(name = "phone")
    var phoneNumber: Int
        private set
        get() = field

    @ColumnInfo(name = "email")
    var email: String
        private set
        get() = field

//    @TypeConverters(addCon::class)
//    @ColumnInfo(name = "sa")
//
//    var sourceAdders: Address
//        set
//        get() = field
//    @TypeConverters(addCon::class)
//    @ColumnInfo(name = "da")
//    var destinationAddress: MutableList<Address>
//        set
//        get() = field

    @ColumnInfo(name = "passengers")
    var passengers: Int
        set
        get() = field

    @ColumnInfo(name = "departureDate")
    var departureDate: String
        set
        get() = field

    @ColumnInfo(name = "returnDate")
    var returnDate: String
        set
        get() = field
    // @Ignore
    // var status: Status
    //  private set
    //  get() = field
    //   @Ignore
    // var serviceProvider: Map<String, Boolean>
    //  private set
    //   get() = field

    constructor(
        id: Int,
        name: String,
        phoneNumber: Int,
        email: String,
//        sourceAdders: Address,
//        destinationAddress: MutableList<Address>,
        passengers: Int,
        departureDate: String,
        returnDate: String,
        // status: Status,
        // serviceProvider: Map<String, Boolean> = mapOf(" " to false),
    ) {
        this.id = id
        this.name = name
        this.phoneNumber = phoneNumber
        this.email = email
//        this.sourceAdders = sourceAdders
//        this.destinationAddress = destinationAddress
        this.passengers = passengers
        this.departureDate = departureDate
        this.returnDate = returnDate
        //  this.status = status
        // this.serviceProvider = serviceProvider
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
