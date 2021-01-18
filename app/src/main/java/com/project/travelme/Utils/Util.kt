package com.project.travelme.Utils

import java.text.SimpleDateFormat

class Util {
    companion object {
        /**
         * This function compare between two dates.
         * @param firstDate The first date
         * @param secondDate The second date
         * @return true if the first date later
         */
        fun compareStringsOfDate(firstDate: String, secondDate: String): Boolean {
            return secondDate != "" && SimpleDateFormat("dd,MM,yyyy").parse(secondDate).time - SimpleDateFormat(
                "dd,MM,yyyy"
            ).parse(firstDate).time < 0
        }

        /**
         * This function checks if the template of email is legal
         * @param str Email
         * @return true if so
         */
        fun isValidEmail(str: String): Boolean {
            val regex = "^([\\w\\.\\-]+)@([\\w\\-]+)((\\.(\\w){2,3})+)\$".toRegex()
            return regex.matches(str)
        }

        /**
         * This function check if the prefix of the phone-number is correct.
         * @param str The phone number
         * @param countryCallingCode The country calling code
         * @return true if so
         */
        fun isValidPhoneNumber(str: String, countryCallingCode: String): Boolean {
            return str.length >= 13 && str.substring(0, 4) == countryCallingCode
        }
    }
}