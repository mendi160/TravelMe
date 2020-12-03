package com.project.travelme

import android.os.Bundle
import android.util.Log
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import com.google.android.libraries.places.api.model.Place
import com.google.android.libraries.places.widget.AutocompleteSupportFragment
import com.google.android.libraries.places.widget.listener.PlaceSelectionListener
import com.project.travelme.Utils.Address
import kotlin.properties.Delegates

class AddressDialog : AppCompatActivity() {
    lateinit var bSave: Button
    private var isSourceAddress by Delegates.notNull<Boolean>()
    private lateinit var currentPlace: String
    private lateinit var destinationAddresses: MutableList<Place>
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        isSourceAddress = intent.getBooleanExtra("bool", false)
        setContentView(R.layout.address_form)
       // val autoTextView = findViewById<AutoCompleteTextView>(R.id.actvCities)
        // Get the array of languages
        val cities = resources.getStringArray(R.array.cities_array)
        // Create adapter and add in AutoCompleteTextView
        val adapter = ArrayAdapter(
            this,
            android.R.layout.simple_list_item_1, cities
        )
        bSave = findViewById<Button>(R.id.bSaveAddress)
        bSave.setOnClickListener {
//            val city =
//                (it.parent as LinearLayout).findViewById<EditText>(R.id.actvCities).text.toString()
//            val street =
//                (it.parent as LinearLayout).findViewById<EditText>(R.id.etStreet).text.toString()
//            val number =
//                (it.parent as LinearLayout).findViewById<EditText>(R.id.etNumber).text.toString()
//            if (number != "" && city != "" && street != "") {

            if (isSourceAddress)
                AddTravelActivity.sourceAddress =currentPlace
            else {
                AddTravelActivity.addressMutableList.add(currentPlace)
                AddTravelActivity.address.notifyDataSetChanged()
            }
            this.finish()
            Toast.makeText(this, "Saved", Toast.LENGTH_SHORT).show()
//            } else {
//                Toast.makeText(
//                    this,
//                    "Please make sure that all fields are filled as required!",
//                    Toast.LENGTH_SHORT
//                ).show()
        }


//        autoTextView.setAdapter(adapter)
//        autoTextView.threshold = 1

        // Initialize the AutocompleteSupportFragment.
        val autocompleteFragment =
            supportFragmentManager.findFragmentById(R.id.autocomplete_fragment)
                    as AutocompleteSupportFragment

        // Specify the types of place data to return.
        autocompleteFragment.setPlaceFields(listOf(Place.Field.ID,Place.Field.NAME,Place.Field.ADDRESS))

        // Set up a PlaceSelectionListener to handle the response.
        autocompleteFragment.setOnPlaceSelectedListener(object : PlaceSelectionListener {
            override fun onPlaceSelected(place: Place) {
                currentPlace=place.address.toString()

            }

            override fun onError(status: com.google.android.gms.common.api.Status) {
                // TODO: Handle the error.
                Log.i("yay", "An error occurred: $status")
            }

        })
    }
}