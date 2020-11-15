package com.project.travelme

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.database.FirebaseDatabase
import com.project.travelme.Entities.Travel
import com.project.travelme.Utils.Address
import com.project.travelme.Utils.Enums.Status
import java.util.*


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        var button: Button = findViewById<Button>(R.id.addButton)
        button.setOnClickListener {
            val i = Intent(this@MainActivity, AddTravelActivity::class.java)
            startActivity(i)
            val database = FirebaseDatabase.getInstance()
            val myRef = database.getReference("Travel")
            val myRef1 = myRef.child("address")
            val trv =  Travel(
                "hj",
                90990808,
                "mend@kj.oko",
                Address("asdasd", "asdsada", 55),
                mutableListOf(Address("asdasd", "asdsada", 55)),
                21,
                GregorianCalendar(2020, 11, 11),
                GregorianCalendar(2021, 1, 1),
                Status.RECEIVED,
                mapOf("dan" to true)
            )
            try {
                myRef.setValue(trv
                  .toMap()
                )
                myRef1.setValue(trv.sourceAdders)
            } catch (e: Exception) {
                Log.i("shit", e.message.toString())
            }
        }

    }
}

