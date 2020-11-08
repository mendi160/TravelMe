package com.project.travelme

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View

class DestinationAddressActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_destination_address)
        this.setFinishOnTouchOutside(false)
    }

    fun showaddadreesdialog(view: View) {
        startActivity(Intent(this, AddressDialog::class.java))
       // var bSave= findViewById<Button>(R.id.bSave).setOnClickListener { Log.i("aaa","aaa") }


    }
}