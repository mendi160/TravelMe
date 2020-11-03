package com.project.travelme.Ui

import android.app.DatePickerDialog
import android.app.Dialog
import android.os.Bundle
import android.view.View
import android.view.Window
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import androidx.core.widget.addTextChangedListener
import com.project.travelme.R
import com.project.travelme.R.id.passengers
import java.text.SimpleDateFormat
import java.util.*

class AddTravelActivity : AppCompatActivity() {
    private lateinit var etPassengers: EditText
    private lateinit var bDeparture: Button
    private lateinit var bReturn: Button

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
        var bSourceAdderss =
            findViewById<Button>(R.id.bSourceAddress)
                .setOnClickListener {
                    showDialog()
                }


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
                if (returnDate != "" && SimpleDateFormat("dd,MM,yyyy").parse(returnDate).time - SimpleDateFormat(
                        "dd,MM,yyyy"
                    ).parse(
                        textV.text.toString()
                    ).time < 0
                )
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


//                    SimpleDateFormat("dd,MM,yyyy").parse(str).time


        dpd.show()
    }

    fun showDialog() {
        val dialog = Dialog(this)
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
}







