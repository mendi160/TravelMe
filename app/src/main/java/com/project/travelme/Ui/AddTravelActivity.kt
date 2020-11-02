package com.project.travelme.Ui

import android.app.DatePickerDialog
import android.app.Dialog
import android.os.Bundle
import android.service.voice.VoiceInteractionSession
import android.view.View
import android.view.Window
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import androidx.core.widget.addTextChangedListener
import com.google.android.material.dialog.MaterialDialogs
import com.project.travelme.R
import com.project.travelme.R.id.passengers
import com.project.travelme.Utils.Address
import java.util.*

class AddTravelActivity : AppCompatActivity() {
    private lateinit var etPassengers: EditText
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_travel)
        Toast.makeText(this, "hi", Toast.LENGTH_LONG).show()
        etPassengers = findViewById<EditText>(passengers)
        etPassengers.addTextChangedListener {
            val text = etPassengers.text.toString()
            if (text != "" && text.toInt() < 0)
                etPassengers.setText("0")
        }
        var bDeparture =
            findViewById<LinearLayout>(R.id.departureDate).findViewById<Button>(R.id.pickDateBtn)
                .setOnClickListener {
                    pickDate(findViewById<LinearLayout>(R.id.departureDate).findViewById<TextView>(R.id.dateTextView))
                }
        var bReturn =
            findViewById<LinearLayout>(R.id.returnDate).findViewById<Button>(R.id.pickDateBtn)
                .setOnClickListener {
                    pickDate(
                        findViewById<LinearLayout>(R.id.returnDate).findViewById<TextView>(
                            R.id.dateTextView
                        )
                    )
                }
        var bSourceAdderss =
            findViewById<Button>(R.id.bSourceAddress)
                .setOnClickListener {
                    showDialog()
                }
    }

    fun less(view: View) {
        if (etPassengers.text.toString() == "")
            return
        etPassengers.setText(
            (etPassengers.text.toString().toInt() - 1).toString()
        )
    }

    fun more(view: View) {
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
        val dpd = DatePickerDialog(
            this,
            DatePickerDialog.OnDateSetListener { view, year, monthOfYear, dayOfMonth ->
                // Display Selected date in textbox
                textV.text = "" + dayOfMonth + " " + (monthOfYear.toInt() + 1) + ", " + year
            },
            year,
            month,
            day
        )
        dpd.datePicker.minDate = System.currentTimeMillis() - 1000
        dpd.show()
    }

    fun showDialog() {

        val spinner: Spinner = findViewById(R.id.sCities)
// Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter.createFromResource(
          this,
            R.array.cities_array,
            android.R.layout.simple_spinner_item
        ).also { adapter ->
            // Specify the layout to use when the list of choices appears
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            // Apply the adapter to the spinner
            spinner.adapter = adapter
        }

        val dialog = Dialog(this)
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        dialog.setTitle("Address")
        dialog.setCancelable(true)
        dialog.setContentView(R.layout.address_form)
        // val body = dialog.findViewById(R.id.body) as TextView
        // body.text = title
        // val yesBtn = dialog.findViewById(R.id.yesBtn) as Button
        // val noBtn = dialog.findViewById(R.id.noBtn) as TextView
        // yesBtn.setOnClickListener {
        //     dialog.dismiss()
        //  }
        // noBtn.setOnClickListener { dialog.dismiss() }
        dialog.show()
    }
}







