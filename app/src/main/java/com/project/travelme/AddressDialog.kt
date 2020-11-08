package com.project.travelme

import android.app.Dialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.Window
import android.widget.ArrayAdapter
import android.widget.AutoCompleteTextView
import android.widget.Spinner

class AddressDialog : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.address_form)
//        dialog = Dialog(view.context)
//        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
//        dialog.setTitle("Address")
//        dialog.setCancelable(true)
//        dialog.setContentView(R.layout.address_form)
// Create an ArrayAdapter using the string array and a default spinner layout
        val autoTextView = findViewById<AutoCompleteTextView>(R.id.actvCities)
        // Get the array of languages
        val cities = resources.getStringArray(R.array.cities_array)
        // Create adapter and add in AutoCompleteTextView
        val adapter = ArrayAdapter(
            this,
            android.R.layout.simple_list_item_1, cities
        )
        autoTextView.setAdapter(adapter)
        autoTextView.threshold = 1


    }

    fun saveAdress(view: View) {
       // Log.i("bul", "bul")

    }
}