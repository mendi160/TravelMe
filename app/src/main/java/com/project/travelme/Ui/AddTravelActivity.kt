package com.project.travelme.Ui

import android.app.DatePickerDialog
import android.app.Dialog
import android.content.Context
import android.content.Intent
import android.content.res.ColorStateList
import android.graphics.Color
import android.os.Bundle
import android.view.View
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import androidx.core.widget.addTextChangedListener
import androidx.core.widget.doOnTextChanged
import androidx.lifecycle.ViewModelProvider
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout
import com.project.travelme.R
import com.project.travelme.R.id.passengers
import com.project.travelme.Utils.Converters
import com.project.travelme.Utils.Enums.Status
import com.project.travelme.Utils.Util
import com.project.travelmedrivers.entities.Travel
import java.text.SimpleDateFormat
import java.util.*
import kotlin.properties.Delegates


class AddTravelActivity : AppCompatActivity() {
    private lateinit var viewModel: TravelViewModel
    lateinit var context: Context
    private lateinit var etPassengers: EditText
    private lateinit var tvDepartureDate: TextView
    private lateinit var tvReturnDate: TextView
    private lateinit var bDeparture: Button
    private lateinit var bReturn: Button
    private lateinit var bSourceAddress: Button
    private lateinit var etEmail: TextInputEditText
    private lateinit var bDestination: Button
    private lateinit var bSave: Button
    private lateinit var dialog: Dialog
    private var debug = true

    private var isSourceAddress by Delegates.notNull<Boolean>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        context = this@AddTravelActivity
        setContentView(R.layout.activity_add_travel)
        this.setFinishOnTouchOutside(false)
        viewModel = ViewModelProvider(this).get(TravelViewModel::class.java)
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
            isSourceAddress = true
            startActivity(Intent(this, AddressDialog::class.java).putExtra("bool", isSourceAddress))


        }
        bDestination = findViewById<Button>(R.id.bDestinationAddress)
        bDestination.setOnClickListener {
            isSourceAddress = false
            startActivity(
                Intent(this, DestinationAddressActivity::class.java)
            )
        }
        bSave = findViewById<Button>(R.id.bSave)
        etEmail = findViewById<TextInputEditText>(R.id.tietEmail)
        etEmail.doOnTextChanged { text, _, _, _ ->
            val d = findViewById<TextInputLayout>(R.id.tilEmail)

            if (!Util.isValidEmail(text.toString())) {
                d.boxStrokeColor = Color.RED
                d.hintTextColor = ColorStateList.valueOf(Color.RED)
            } else {
                d.boxStrokeColor = Color.GREEN
                d.hintTextColor = ColorStateList.valueOf(Color.GREEN)
            }
        }
        tvDepartureDate =
            findViewById<LinearLayout>(R.id.departureDate).findViewById<TextView>(R.id.dateTextView)
        tvReturnDate =
            findViewById<LinearLayout>(R.id.returnDate).findViewById<TextView>(R.id.dateTextView)

        addressMutableList = mutableListOf()
        address = ArrayAdapter(this, android.R.layout.simple_list_item_1, addressMutableList)
        viewModel.getIsSuccess().observe(this, {
            if (it)
                Toast.makeText(this, "Saved", Toast.LENGTH_SHORT).show()
            else
                Toast.makeText(this, "not saved", Toast.LENGTH_SHORT).show()
        })
    }

    companion object {
        var sourceAddress: String = ""
        lateinit var addressMutableList: MutableList<String>
        lateinit var address: ArrayAdapter<String>
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
        val departureDate = tvDepartureDate.text.toString()
        val returnDate = tvReturnDate.text.toString()
        val dpd = DatePickerDialog(
            this,
            DatePickerDialog.OnDateSetListener { view, year, monthOfYear, dayOfMonth ->
                // Display Selected date in textbox
                textV.text = "" + dayOfMonth + "," + (monthOfYear.toInt() + 1) + "," + year
                bReturn.isEnabled = true
                if (Util.compareStringsOfDate(textV.text.toString(), returnDate))
                    tvReturnDate.text = tvDepartureDate.text.toString()
            },
            year,
            month,
            day
        )
        if (tvDepartureDate == textV)
            dpd.datePicker.minDate = System.currentTimeMillis() - 1000
        else
            dpd.datePicker.minDate = SimpleDateFormat("dd,MM,yyyy").parse(departureDate).time
        dpd.show()
    }

    fun save(view: View) {
        if (!debug) {
            val name = findViewById<TextInputEditText>(R.id.etName).text.toString()
            val email = etEmail.text.toString()
            val departureDate = tvDepartureDate.text.toString()
            val returnDate = tvReturnDate.text.toString()
            val sourceAddress = sourceAddress
            val destAddress = addressMutableList
            val phoneNumber = findViewById<TextInputEditText>(R.id.etPhone).text.toString()
            val passengers = etPassengers.text.toString()
            val status: Status =
                Converters.fromStringToStatus(findViewById<TextInputEditText>(R.id.etStatus1).text.toString())!!
            val list = listOf<String>(
                name,
                email,
                departureDate,
                returnDate,
                sourceAddress,
                phoneNumber,
                passengers,
                status.name
            )
            if (destAddress.size == 0 || !list.all { x -> x != "" } || !Util.isValidEmail(email)) {
                MaterialAlertDialogBuilder(this).setTitle("Error")
                    .setMessage("Please make sure everything is correct")
                    .setNeutralButton("OK") { which, _ -> which.dismiss() }.show()
                return
            }

            val travel = Travel()
            travel.id = "1"
            travel.name = name
            travel.phoneNumber = phoneNumber
            travel.email = email
            travel.sourceAdders = sourceAddress
            travel.destinationAddress = destAddress
            travel.passengers = passengers.toInt()
            travel.departureDate = departureDate
            travel.returnDate = returnDate
            travel.status = status
            try {
                viewModel.insertTravel(context, travel)
            } catch (e: Exception) {
                print(e.message)
            }
        } else {
            val travel = Travel()
            travel.id = "1"
            travel.name = "name"
            travel.phoneNumber = "+972526077044"
            travel.email = "email@email.com"
            travel.sourceAdders = "source Address"
            travel.destinationAddress = mutableListOf<String>("destinations1", "destination2")
            travel.passengers = 10
            travel.departureDate = "departureDate"
            travel.returnDate = "returnDate"
            travel.status = Status.SENT

            try {
                viewModel.insertTravel(context, travel)
            } catch (e: Exception) {
                print(e.message)
            }
        }
    }
}







