package com.project.travelme

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class DestinationAddressActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_destination_address)
        this.setFinishOnTouchOutside(false)
    }
}