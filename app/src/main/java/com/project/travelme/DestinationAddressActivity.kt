package com.project.travelme

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.ArrayAdapter
import android.widget.ListView
import androidx.appcompat.app.AppCompatActivity
import com.project.travelme.Utils.Address

class DestinationAddressActivity : AppCompatActivity() {
    lateinit var address: ArrayAdapter<Address>
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_destination_address)
        this.setFinishOnTouchOutside(false)
        var addressArray = arrayOf(Address("Tel-Aviv", "alanbi", 12))

        var mListView = findViewById<ListView>(R.id.lvAddress)
        address = ArrayAdapter(this,addressArray)
            this
            android.R.layout.simple_list_item_1, addressArray
        )
        mListView.adapter = address
    }

    fun showAddAddressDialog(view: View) {
        startActivity(Intent(this, AddressDialog::class.java))
    }
}