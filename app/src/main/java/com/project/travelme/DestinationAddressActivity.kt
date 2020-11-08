package com.project.travelme

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.LinearLayout
import android.widget.ListView

class DestinationAddressActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_destination_address)
        this.setFinishOnTouchOutside(false)
    }

    fun showaddadreesdialog(view: View) {
        startActivity(Intent(this, Address::class.java))
       // var bSave= findViewById<Button>(R.id.bSave).setOnClickListener { Log.i("aaa","aaa") }


    }
}