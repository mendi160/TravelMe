package com.project.travelme

import android.os.Bundle
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import com.project.travelme.Ui.AddTravelActivity
import com.project.travelme.Utils.Address
import kotlin.properties.Delegates

class AddressDialog : AppCompatActivity() {
    lateinit var bSave: Button
    private var isSourceAddress by Delegates.notNull<Boolean>()
    private lateinit var sourceAddress: Address
    private lateinit var destinationAddresses: MutableList<Address>
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        isSourceAddress = intent.getBooleanExtra("bool", false)
        setContentView(R.layout.address_form)
        val autoTextView = findViewById<AutoCompleteTextView>(R.id.actvCities)
        // Get the array of languages
        val cities = resources.getStringArray(R.array.cities_array)
        // Create adapter and add in AutoCompleteTextView
        val adapter = ArrayAdapter(
            this,
            android.R.layout.simple_list_item_1, cities
        )
        bSave = findViewById<Button>(R.id.bSaveAddress)
        bSave.setOnClickListener {
            val city =
                (it.parent as LinearLayout).findViewById<EditText>(R.id.actvCities).text.toString()
            val street =
                (it.parent as LinearLayout).findViewById<EditText>(R.id.etStreet).text.toString()
            val number =
                (it.parent as LinearLayout).findViewById<EditText>(R.id.etNumber).text.toString()
            if (number != "" && city != "" && street != "") {

                if (isSourceAddress)
                    AddTravelActivity.sourceAddress = Address(city, street, number.toInt())
                else {
                    AddTravelActivity.addressMutableList.add(Address(city, street, number.toInt()))
                    AddTravelActivity.address.notifyDataSetChanged()
                }
                this.finish()
                Toast.makeText(this, "Saved", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(
                    this,
                    "Please make sure that all fields are filled as required!",
                    Toast.LENGTH_SHORT
                ).show()
            }
        }
        autoTextView.setAdapter(adapter)
        autoTextView.threshold = 1
    }
}