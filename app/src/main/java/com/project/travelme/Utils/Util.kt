package com.project.travelme.Utils

import java.text.SimpleDateFormat

class Util {
    companion object {
        /**
         * This function compare between two dates.
         *  Return true if the first date later
         */
        fun compareStringsOfDate(firstDate: String, secondDate: String): Boolean {
            return secondDate != "" && SimpleDateFormat("dd,MM,yyyy").parse(secondDate).time - SimpleDateFormat(
                "dd,MM,yyyy"
            ).parse(firstDate).time < 0
        }
    }
}