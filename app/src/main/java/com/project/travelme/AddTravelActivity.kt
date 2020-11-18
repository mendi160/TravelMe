package com.project.travelme

import android.app.AlertDialog
import android.app.DatePickerDialog
import android.app.Dialog
import android.content.Intent
import android.content.res.ColorStateList
import android.graphics.Color
import android.os.Bundle
import android.view.View
import android.view.Window
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import androidx.core.widget.addTextChangedListener
import androidx.core.widget.doOnTextChanged
import com.google.android.material.dialog.MaterialAlertDialogBuilder

import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout
import com.project.travelme.Entities.Travel
import com.project.travelme.R.id.passengers
import com.project.travelme.Ui.TravelViewModel
import com.project.travelme.Utils.Address
import com.project.travelme.Utils.Converters
import com.project.travelme.Utils.Enums.Status
import com.project.travelme.Utils.Util
import java.text.SimpleDateFormat
import java.util.*
import kotlin.properties.Delegates


class AddTravelActivity : AppCompatActivity() {
    private lateinit var etPassengers: EditText
    private lateinit var tvDepartureDate: TextView
    private lateinit var tvReturnDate: TextView
    private lateinit var bDeparture: Button
    private lateinit var bReturn: Button
    private lateinit var bSourceAddress: Button
    private lateinit var etEmail: TextInputEditText
    private lateinit var bDestination: Button
    private lateinit var bSave: Button
    private lateinit var viewModel: TravelViewModel
    private lateinit var dialog: Dialog


    private var isSourceAddress by Delegates.notNull<Boolean>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_travel)
        this.setFinishOnTouchOutside(false)
        viewModel = TravelViewModel()//.of(this).get(TravelViewModel::class.java)
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
            //showDialog(it)
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
        etEmail.doOnTextChanged { text, start, before, count ->
            var d = findViewById<TextInputLayout>(R.id.tilEmail)

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

        addressMutableList = mutableListOf(Address("Tel-Aviv", "alanbi", 12))
        address = ArrayAdapter(this, android.R.layout.simple_list_item_1, addressMutableList)
        viewModel.getIsSuccess().observe(this, {
            if (it)
                Toast.makeText(this, "Saved", Toast.LENGTH_SHORT).show()
            else
                Toast.makeText(this, "fucked", Toast.LENGTH_SHORT).show()
        })
    }

    companion object {

        var sourceAddress: Address = Address("", "", 0)
        lateinit var addressMutableList: MutableList<Address>
        lateinit var address: ArrayAdapter<Address>
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
        var departureDate = tvDepartureDate.text.toString()
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

    fun showDialog(view: View) {
        dialog = Dialog(view.context)
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

    fun save(view: View) {
        var name = findViewById<TextInputEditText>(R.id.etName).text.toString()
        var email = etEmail.text.toString()
        var departureDate = tvDepartureDate.text.toString()
        var returnDate = tvReturnDate.text.toString()
        var sourceAddress = sourceAddress
        var destAddress = addressMutableList
        var phoneNumber = findViewById<TextInputEditText>(R.id.etPhone).text.toString()
        var passengers = etPassengers.text.toString()
        var status: Status =
            Converters.fromStringToStatus(findViewById<TextInputEditText>(R.id.etStatus1).text.toString())!!
        var list = listOf<String>(
            name,
            email,
            departureDate,
            returnDate,
            sourceAddress._city,
            destAddress[0]._city,
            phoneNumber,
            passengers,
            status.name
        )
        if (true && !list.all { x-> x!="" }|| !Util.isValidEmail(email)) {
            MaterialAlertDialogBuilder(this).setTitle("Error")
                .setMessage("Please make sure everything is correct")
                .setNeutralButton("OK") { which, _ -> which.dismiss() }.show()
            return
        }


        var travel = Travel(
            name,
            phoneNumber.toInt(),
            email,
            sourceAddress,
            destAddress,
            passengers.toInt(),
            Converters.fromStringToGeorgianCalender(departureDate),
            Converters.fromStringToGeorgianCalender(returnDate),
            status
        )
        try {
            viewModel.saveTravel(travel)
        } catch (e: Exception) {
            print(e.message)
        }
    }
}







