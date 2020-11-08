package com.project.travelme

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity

class DestinationAddressActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_destination_address)
        this.setFinishOnTouchOutside(false)
    }

    fun showAddAddressDialog(view: View) {
        startActivity(Intent(this, AddressDialog::class.java))
    }
}