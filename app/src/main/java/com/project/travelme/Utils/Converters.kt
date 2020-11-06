package com.project.travelme.Utils

import com.project.travelme.Utils.Enums.Status

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

        fun fromStringTStatus(status: String): Status? {
            return stringStatusMap[status]
        }
    }
}