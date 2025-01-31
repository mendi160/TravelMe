package com.project.travelme.Ui

import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.android.libraries.places.api.Places
import com.google.android.libraries.places.api.model.Place
import com.google.android.libraries.places.widget.AutocompleteSupportFragment
import com.google.android.libraries.places.widget.listener.PlaceSelectionListener
import com.project.travelme.R
import kotlin.properties.Delegates

class AddressDialog : AppCompatActivity() {
    private lateinit var bSave: Button
    private var isSourceAddress by Delegates.notNull<Boolean>()
    private lateinit var currentPlace: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        isSourceAddress = intent.getBooleanExtra("bool", false)
        setContentView(R.layout.address_form)
        bSave = findViewById(R.id.bSaveAddress)
        val api = "AIzaSyBUPxQMO2iI0DS_WTeetlcND9mpWaUCyyY"
        Places.initialize(applicationContext, api)

        // Initialize the AutocompleteSupportFragment.
        val autocompleteFragment =
            supportFragmentManager.findFragmentById(R.id.autocomplete_fragment)
                    as AutocompleteSupportFragment

        // Specify the types of place data to return.
        autocompleteFragment.setPlaceFields(
            listOf(
                Place.Field.ID,
                Place.Field.NAME,
                Place.Field.ADDRESS,
                Place.Field.LAT_LNG
            )
        ).setCountries(
            mutableListOf("IL")
        )
        // Set up a PlaceSelectionListener to handle the response.
        autocompleteFragment.setOnPlaceSelectedListener(object : PlaceSelectionListener {
            override fun onPlaceSelected(place: Place) {
                currentPlace = place.address.toString() + "&" + place.latLng.toString()
            }

            override fun onError(status: com.google.android.gms.common.api.Status) {
                // TODO: Handle the error.
                Log.i("yay", "An error occurred: $status")
            }

        })
        bSave.setOnClickListener {

            if (isSourceAddress)
                AddTravelActivity.sourceAddress = currentPlace
            else {
                AddTravelActivity.firebaseAddressMutableList.add(currentPlace)
                AddTravelActivity.viewAddress.add(currentPlace.substringBefore("&"))
                AddTravelActivity.address.notifyDataSetChanged()
            }
            Toast.makeText(this, "Address Saved", Toast.LENGTH_SHORT).show()
            this.finish()
        }
    }
}