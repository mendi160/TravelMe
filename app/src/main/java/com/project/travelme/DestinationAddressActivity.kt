package com.project.travelme

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ListView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.project.travelme.Utils.Address


class DestinationAddressActivity : AppCompatActivity() {
    lateinit var address: ArrayAdapter<Address>
    lateinit var addressArray : Array<Address>
    lateinit var mListView: ListView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_destination_address)
        this.setFinishOnTouchOutside(false)
         addressArray = arrayOf(Address("Tel-Aviv", "alanbi", 12))
         mListView = findViewById<ListView>(R.id.lvAddress)
        address= ArrayAdapter(this,android.R.layout.simple_list_item_1,addressArray)
        mListView.adapter=address



    }

    override fun onResume() {
        super.onResume()
        mListView.adapter=address
    }



    fun showAddAddressDialog(view: View) {

        startActivity(Intent(this, AddressDialog::class.java).putExtra("ad",addressArray))


    }
}