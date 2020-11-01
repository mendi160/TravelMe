package com.project.travelme.Ui

import android.app.DatePickerDialog
import android.app.Dialog
import android.os.Bundle
import android.view.View
import android.view.Window
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import androidx.core.widget.addTextChangedListener
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.project.travelme.R
import com.project.travelme.R.id.passengers
import java.time.LocalDate
import java.time.LocalTime
import java.util.*

class AddTravelActivity : AppCompatActivity() {
    private lateinit var passengersEditText: EditText
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_add_travel)
        Toast.makeText(this, "hi", Toast.LENGTH_LONG).show()
        passengersEditText = findViewById<EditText>(passengers)
        passengersEditText.addTextChangedListener {
            val text = passengersEditText.text.toString()
            if (text != "" && text.toInt() < 0)
                passengersEditText.setText("0")
        }
        var departBtn =
            findViewById<LinearLayout>(R.id.departureDate).findViewById<Button>(R.id.pickDateBtn)
                .setOnClickListener {
                    pickDate(findViewById<LinearLayout>(R.id.departureDate).findViewById<TextView>(R.id.dateTextView))


                }
        var returnBtn =
            findViewById<LinearLayout>(R.id.returnDate).findViewById<Button>(R.id.pickDateBtn)
                .setOnClickListener {
                    pickDate(
                        findViewById<LinearLayout>(R.id.returnDate).findViewById<TextView>(
                            R.id.dateTextView
                        )
                    )
                }
    }

    fun less(view: View) {
        if (passengersEditText.text.toString() == "")
            return
        passengersEditText.setText(
            (passengersEditText.text.toString().toInt() - 1).toString()
        )
    }

    fun more(view: View) {

        if (passengersEditText.text.toString() == "")
            passengersEditText.setText("1")
        else {
            passengersEditText.setText(
                (passengersEditText.text.toString().toInt() + 1).toString()
            )
        }
        showDialog()
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
        dpd.datePicker.minDate=System.currentTimeMillis()-1000
        dpd.show()
    }
    fun showDialog(){
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







