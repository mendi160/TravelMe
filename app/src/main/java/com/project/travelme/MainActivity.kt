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
        }

    }
}

