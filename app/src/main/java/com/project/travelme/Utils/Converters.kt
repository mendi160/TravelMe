package com.project.travelme.Utils

import androidx.room.TypeConverter
import com.project.travelme.Utils.Enums.Status
import java.text.SimpleDateFormat
import java.util.*

// example converter for java.util.Date
class Converters {

    companion object {

        var stringStatusMap: HashMap<String, Status> = hashMapOf(
            "Running" to Status.RUNNING,
            "Sent" to Status.SENT, "Received" to Status.RECEIVED,
            "Closed" to Status.CLOSED
        )

        fun fromStatusToString(status: Status): String {
            return stringStatusMap.filterValues { it == status }.keys.toString()
        }

        fun fromStringToStatus(status: String): Status? {
            return stringStatusMap[status]
        }

        fun fromGeorgianCalenderToString(date: GregorianCalendar): String {
            return "${date.get(Calendar.DAY_OF_MONTH)},${date.get(Calendar.MONTH)},${
                date.get(
                    Calendar.YEAR
                )
            }"
        }

        fun fromStringToGeorgianCalender(date: String): GregorianCalendar {
            val Date = Date(SimpleDateFormat("dd,MM,yyyy").parse(date).time)
            return GregorianCalendar(Date.day, Date.month, Date.year)
        }

    }
}
class addCon{
    @TypeConverter
    fun fromAddressToString(add:Address):String{
        return add.toString()
    }
    @TypeConverter
    fun fromStringToAddress(add:Address):Address{
        return Address("kak","aa",1)
    }
}