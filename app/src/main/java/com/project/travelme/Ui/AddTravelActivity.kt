package com.project.travelme.Ui

import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.widget.addTextChangedListener
import com.project.travelme.R
import com.project.travelme.R.id.passengers

class AddTravelActivity : AppCompatActivity() {
    private lateinit var passengersEditText:EditText
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_travel)
        Toast.makeText(this, "hi", Toast.LENGTH_LONG).show()
       passengersEditText = findViewById<EditText>(passengers)
        passengersEditText.addTextChangedListener {
            val text = passengersEditText.text.toString()
            if (text == "" || text.toInt() < 0)
                passengersEditText.setText("0")
        }
    }
    fun less(view: View) {
        setEmptyEditTextToZero()
        passengersEditText.setText(
            (passengersEditText.text.toString().toInt() - 1).toString()
        )
    }

    fun more(view: View) {
        setEmptyEditTextToZero()
        passengersEditText.setText(
            (passengersEditText.text.toString().toInt() + 1).toString()
        )
    }
    private fun setEmptyEditTextToZero(){
        if (passengersEditText.text.toString() == "")
            passengersEditText.setText("0")
    }
}
