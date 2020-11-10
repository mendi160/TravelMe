package com.project.travelme

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.ListView
import androidx.appcompat.app.AppCompatActivity

class DestinationAddressActivity : AppCompatActivity() {

    lateinit var mListView: ListView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_destination_address)
        this.setFinishOnTouchOutside(false)
        mListView = findViewById<ListView>(R.id.lvAddress)
        mListView.adapter = AddTravelActivity.address
    }

    fun showAddAddressDialog(view: View) {
        startActivity(Intent(this, AddressDialog::class.java))
    }
}