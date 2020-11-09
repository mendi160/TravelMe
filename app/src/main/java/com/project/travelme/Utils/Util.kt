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

        fun isNumber(str: String): Boolean {
            try {
                str.toInt()
                return true
            } catch (e: Exception) {

                return false
            }
        }

        fun isValidEmail(str: String): Boolean {
            val regex = "^([\\w\\.\\-]+)@([\\w\\-]+)((\\.(\\w){2,3})+)\$".toRegex()
            return regex.matches(str)
            return regex.containsMatchIn(str)
        }
    }
}