package com.project.travelme.Entities

import com.project.travelme.Utils.Address
import com.project.travelme.Utils.Enums.Status
import java.time.LocalDate

class Travel {
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
}
/*
✔	שם לקוח
✔	טלפון לקוח
✔	כתובת דוא"ל לקוח.
✔	כתובת איסוף וחזרה
✔	כתובת יעד (יתכנו מספר יעדים)
✔	מספר נוסעים.
✔	תאריך יציאה
✔	תאריך חזרה
✔	סטאטוס הדרישה (נשלחה ובהמתנה לאישור לקוח. התקבלה הצעה לנתינת שירות. בהרצה, כלומר הנסיעה מתבצעת כרגע. סגורה, כלומר הנסיעה הסתיימה)
✔	שם החברה שהתקבלה לבצע את ההסעה (ימולא אוטומטית "NO") ימומש באמצעות HashMap<String,Boolean> כאשר כתובת הדוא"ל של חברת ההסעות משמשת כמפתח והאם החברה מאושרת ע"י הלקוח או לא מאושרת את זה מייצג את הערך הבוליאני.

 */