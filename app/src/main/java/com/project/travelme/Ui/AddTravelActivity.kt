package com.project.travelme.Ui

import android.app.DatePickerDialog
import android.app.Dialog
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.view.Window
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import androidx.core.widget.addTextChangedListener
import com.project.travelme.R
import com.project.travelme.R.id.passengers
import com.project.travelme.Utils.Address
import com.project.travelme.Utils.Util
import java.text.SimpleDateFormat
import java.util.*

class AddTravelActivity : AppCompatActivity() {
    private lateinit var etPassengers: EditText
    private lateinit var bDeparture: Button
    private lateinit var bReturn: Button
    private lateinit var bSourceAddress: Button
    private lateinit var bDestination: Button
    private lateinit var bSave: Button
    private lateinit var dialog :Dialog
    private lateinit var sourceAddress: Address
    private lateinit var destinationAddresses: MutableList<Address>
    private var isSourceAddress: Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_travel)

        etPassengers = findViewById<EditText>(passengers)
        etPassengers.addTextChangedListener {
            val text = etPassengers.text.toString()
            if (text != "" && text.toInt() < 0)
                etPassengers.setText("0")
        }
        //initialize buttons for pick date
        bDeparture =
            findViewById<LinearLayout>(R.id.departureDate).findViewById<Button>(R.id.pickDateBtn)
        bReturn = findViewById<LinearLayout>(R.id.returnDate).findViewById<Button>(R.id.pickDateBtn)
        bReturn.isEnabled = false
        bDeparture.setOnClickListener {
            pickDate(findViewById<LinearLayout>(R.id.departureDate).findViewById<TextView>(R.id.dateTextView))

        }
        bDeparture.text = "Depart Date"
        bReturn.setOnClickListener {
            pickDate(
                findViewById<LinearLayout>(R.id.returnDate).findViewById<TextView>(
                    R.id.dateTextView
                )
            )
        }
        bReturn.text = "Return Date"
        bSourceAddress = findViewById<Button>(R.id.bSourceAddress)
        bSourceAddress.setOnClickListener {
            showDialog()
            isSourceAddress = true
        }
        bDestination = findViewById<Button>(R.id.bDestinationAddress)
        bDestination.setOnClickListener {
            //showDialog()

            isSourceAddress = false
        }
        bSave = findViewById<Button>(R.id.bSave)
       //TODO bSave.setOnClickListener { }
        destinationAddresses= mutableListOf()
    }

    fun removePassenger(view: View) {
        if (etPassengers.text.toString() == "")
            return
        etPassengers.setText(
            (etPassengers.text.toString().toInt() - 1).toString()
        )
    }

    fun addPassenger(view: View) {
        if (etPassengers.text.toString() == "")
            etPassengers.setText("1")
        else {
            etPassengers.setText(
                (etPassengers.text.toString().toInt() + 1).toString()
            )
        }
    }

    fun pickDate(textV: TextView) {
        val c = Calendar.getInstance()
        val year = c.get(Calendar.YEAR)
        val month = c.get(Calendar.MONTH).toInt()
        val day = c.get(Calendar.DAY_OF_MONTH)
        val departureTextView: TextView =
            findViewById<LinearLayout>(R.id.departureDate).findViewById<TextView>(R.id.dateTextView)
        val returnTextView: TextView =
            findViewById<LinearLayout>(R.id.returnDate).findViewById<TextView>(R.id.dateTextView)
        var departureDate = departureTextView.text.toString()
        val returnDate = returnTextView.text.toString()
        val dpd = DatePickerDialog(
            this,
            DatePickerDialog.OnDateSetListener { view, year, monthOfYear, dayOfMonth ->
                // Display Selected date in textbox
                textV.text = "" + dayOfMonth + "," + (monthOfYear.toInt() + 1) + "," + year
                bReturn.isEnabled = true
                if (Util.compareStringsOfDate(textV.text.toString(), returnDate))
                    returnTextView.text = departureTextView.text.toString()
            },
            year,
            month,
            day
        )
        if (departureTextView == textV)
            dpd.datePicker.minDate = System.currentTimeMillis() - 1000
        else
            dpd.datePicker.minDate = SimpleDateFormat("dd,MM,yyyy").parse(departureDate).time
        dpd.show()
    }

    private fun showDialog() {
        dialog = Dialog(this)
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        dialog.setTitle("Address")
        dialog.setCancelable(true)
        dialog.setContentView(R.layout.address_form)
// Create an ArrayAdapter using the string array and a default spinner layout
        val autoTextView = dialog.findViewById<AutoCompleteTextView>(R.id.actvCities)
        // Get the array of languages
        val cities = resources.getStringArray(R.array.cities_array)
        // Create adapter and add in AutoCompleteTextView
        val adapter = ArrayAdapter(
            this,
            android.R.layout.simple_list_item_1, cities
        )
        autoTextView.setAdapter(adapter)
        autoTextView.threshold = 1
        dialog.show()
    }

    fun saveAddress(view: View) {
        val city =
            (view.parent as LinearLayout).findViewById<EditText>(R.id.actvCities).text.toString()
        val street =
            (view.parent as LinearLayout).findViewById<EditText>(R.id.etStreet).text.toString()
        val number =
            (view.parent as LinearLayout).findViewById<EditText>(R.id.etNumber).text.toString()
        if (isSourceAddress)
            sourceAddress = Address(city, street, number.toInt())
        else
            destinationAddresses.add(Address(city,street,number.toInt()))
        dialog.cancel()
        Toast.makeText(this,"Saved",Toast.LENGTH_SHORT).show()
    }
}







